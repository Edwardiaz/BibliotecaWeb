<%-- 
    Document   : listar
    Created on : Nov 30, 2022, 8:02:34 PM
    Author     : Eduardo Trujillo
--%>

<%@page import="java.util.Iterator"%>
<%@page import="Modelo.Genero"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.GeneroDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file='/assets.jsp' %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Generos</title>
    </head>
    <body>
        <jsp:include page="/menu.jsp"/>
        <div class="container-fluid">
            <h1>Listado de Generos</h1>
            <a class="btn btn-success" href="${contextPath}/GeneroControlador?accion=add" style="margin-bottom:10px">Agregar Nuevo Genero</a>
            <table id="table" class="table table-striped table-bordered dt-responsive nowrap rounded" style="width:100%">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nombre de Genero</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        GeneroDAO dao = new GeneroDAO();
                        List<Genero> list = dao.listar();
                        Iterator<Genero> iter = list.iterator();
                        Genero genero = null;                    
                        while(iter.hasNext()){
                            genero = iter.next();
                    %>
                        <tr>
                            <td><%= genero.getId()%></td>
                            <td><%= genero.getNombre_genero()%></td>
                            <td>
                                <a class="btn btn-sm btn-info" href="${contextPath}/GeneroControlador?accion=editar&id=<%= genero.getId()%>">Editar</a>
                                <a class="btn btn-sm btn-danger" href="javascript:eliminar(<%= genero.getId()%>)">Eliminar</a>
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
  alertify.confirm("¿Realmente desea eliminar este Genero?", function(e){
     if(e){
         location.href="GeneroControlador?accion=eliminar&id="+ id;
     } 
  });
}
</script>
