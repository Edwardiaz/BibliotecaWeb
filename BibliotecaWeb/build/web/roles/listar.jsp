<%-- 
    Document   : listar
    Created on : Nov 30, 2022, 8:02:34 PM
    Author     : Eduardo Trujillo
--%>

<%@page import="java.util.Iterator"%>
<%@page import="Modelo.Rol"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.RolDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file='/assets.jsp' %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Roles</title>
    </head>
    <body>
        <jsp:include page="/menu.jsp"/>
        <div class="container-fluid">
            <h1>Listado de Roles del Sistema</h1>
            <a class="btn btn-success" href="${contextPath}/RolControlador?accion=add" style="margin-bottom:10px">Agregar Nuevo Rol</a>
            <table id="table" class="table table-striped table-bordered dt-responsive nowrap rounded" style="width:100%">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Rol</th>
                        <th>Numero Prestamos</th>
                        <th>Dias Prestamo</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        RolDAO dao = new RolDAO();
                        List<Rol> list = dao.listar();
                        Iterator<Rol> iter = list.iterator();
                        Rol rol = null;                    
                        while(iter.hasNext()){
                            rol = iter.next();
                    %>
                        <tr>
                            <td><%= rol.getId()%></td>
                            <td><%= rol.getRol()%></td>
                            <td><%= rol.getNumero_prestamos()%></td>
                            <td><%= rol.getDias_prestamo()%></td>
                            <td>
                                <a class="btn btn-sm btn-info" href="${contextPath}/RolControlador?accion=editar&id=<%= rol.getId()%>">Editar</a>
                                <a class="btn btn-sm btn-danger" href="javascript:eliminar(<%= rol.getId()%>)">Eliminar</a>
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
  alertify.confirm("¿Realmente decea eliminar este Rol?", function(e){
     if(e){
         location.href="RolControlador?accion=eliminar&id="+ id;
     } 
  });
}
</script>
