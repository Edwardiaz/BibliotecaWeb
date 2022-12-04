<%-- 
    Document   : listar
    Created on : Nov 30, 2022, 8:02:34 PM
    Author     : Eduardo Trujillo
--%>

<%@page import="java.util.Iterator"%>
<%@page import="Modelo.Autor"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.AutorDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file='/assets.jsp' %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Autores</title>
    </head>
    <body>
        <jsp:include page="/menu.jsp"/>
        <div class="container-fluid">
            <h1>Listado de Autores</h1>
            <a class="btn btn-success" href="${contextPath}/AutorControlador?accion=add" style="margin-bottom:10px">Agregar Nuevo Autor</a>
            <table id="table" class="table table-striped table-bordered dt-responsive nowrap rounded" style="width:100%">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nombre de Autor</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        AutorDAO dao = new AutorDAO();
                        List<Autor> list = dao.listar();
                        Iterator<Autor> iter = list.iterator();
                        Autor autor = null;                    
                        while(iter.hasNext()){
                            autor = iter.next();
                    %>
                        <tr>
                            <td><%= autor.getId()%></td>
                            <td><%= autor.getNombre_autor()%></td>
                            <td>
                                <a class="btn btn-sm btn-info" href="${contextPath}/AutorControlador?accion=editar&id=<%= autor.getId()%>">Editar</a>
                                <a class="btn btn-sm btn-danger" href="javascript:eliminar(<%= autor.getId()%>)">Eliminar</a>
                            </tr>
                    <%}%>
                </tbody>
            </table>
        </div>
    </body>
</html>

<script type="text/javascript">
$(document).ready(function() {
    $('#table').DataTable();
} );

<c:if test="${not empty exito}">
    alertify.success('${exito}');
   <c:set var="exito" value="" scope="session" />
</c:if>
    <c:if test="${not empty error}">
    alertify.error('${error}');
    <c:set var="error" value="" scope="session" />
</c:if>
    
function eliminar(id){
  alertify.confirm("¿Realmente decea eliminar este Autor?", function(e){
     if(e){
         location.href="AutorControlador?accion=eliminar&id="+ id;
     } 
  });
}
</script>
