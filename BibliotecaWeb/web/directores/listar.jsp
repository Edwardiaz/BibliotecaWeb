<%-- 
    Document   : listar
    Created on : Nov 30, 2022, 8:02:34 PM
    Author     : Eduardo Trujillo
--%>

<%@page import="java.util.Iterator"%>
<%@page import="Modelo.Director"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.DirectorDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file='/assets.jsp' %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Directores</title>
    </head>
    <body>
        <jsp:include page="/menu.jsp"/>
        <div class="container-fluid">
            <h1>Listado de Directores</h1>
            <a class="btn btn-success" href="${contextPath}/DirectorControlador?accion=add" style="margin-bottom:10px">Agregar Nuevo Director</a>
            <table id="table" class="table table-striped table-bordered dt-responsive nowrap rounded" style="width:100%">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nombre de Director</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        DirectorDAO dao = new DirectorDAO();
                        List<Director> list = dao.listar();
                        Iterator<Director> iter = list.iterator();
                        Director director = null;                    
                        while(iter.hasNext()){
                            director = iter.next();
                    %>
                        <tr>
                            <td><%= director.getId()%></td>
                            <td><%= director.getNombre_director()%></td>
                            <td>
                                <a class="btn btn-sm btn-info" href="${contextPath}/DirectorControlador?accion=editar&id=<%= director.getId()%>">Editar</a>
                                <a class="btn btn-sm btn-danger" href="javascript:eliminar(<%= director.getId()%>)">Eliminar</a>
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
  alertify.confirm("¿Realmente desea eliminar este Director?", function(e){
     if(e){
         location.href="DirectorControlador?accion=eliminar&id="+ id;
     } 
  });
}
</script>
