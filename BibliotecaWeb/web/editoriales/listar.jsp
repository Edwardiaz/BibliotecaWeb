<%-- 
    Document   : listar
    Created on : Nov 30, 2022, 8:02:34 PM
    Author     : Eduardo Trujillo
--%>

<%@page import="java.util.Iterator"%>
<%@page import="Modelo.Editorial"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.EditorialDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file='/assets.jsp' %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Editoriales</title>
    </head>
    <body>
        <jsp:include page="/menu.jsp"/>
        <div class="container-fluid">
            <h1>Listado de Editoriales</h1>
            <a class="btn btn-success" href="${contextPath}/EditorialControlador?accion=add" style="margin-bottom:10px">Agregar Nuevo Editorial</a>
            <table id="table" class="table table-striped table-bordered dt-responsive nowrap rounded" style="width:100%">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nombre de Editorial</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        EditorialDAO dao = new EditorialDAO();
                        List<Editorial> list = dao.listar();
                        Iterator<Editorial> iter = list.iterator();
                        Editorial editorial = null;                    
                        while(iter.hasNext()){
                            editorial = iter.next();
                    %>
                        <tr>
                            <td><%= editorial.getId()%></td>
                            <td><%= editorial.getNombre_editorial()%></td>
                            <td>
                                <a class="btn btn-sm btn-info" href="${contextPath}/EditorialControlador?accion=editar&id=<%= editorial.getId()%>">Editar</a>
                                <a class="btn btn-sm btn-danger" href="javascript:eliminar(<%= editorial.getId()%>)">Eliminar</a>
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
  alertify.confirm("¿Realmente decea eliminar este Editorial?", function(e){
     if(e){
         location.href="EditorialControlador?accion=eliminar&id="+ id;
     } 
  });
}
</script>
