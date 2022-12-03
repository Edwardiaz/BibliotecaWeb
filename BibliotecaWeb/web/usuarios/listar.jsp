<%-- 
    Document   : listar
    Created on : Nov 30, 2022, 8:02:34 PM
    Author     : Eduardo Trujillo
--%>

<%@page import="java.util.Iterator"%>
<%@page import="Modelo.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file='/assets.jsp' %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Usuarios</title>
    </head>
    <body>
        <jsp:include page="/menu.jsp"/>
        <div class="container-fluid">
            <h1>Listado de Usuarios del Sistema</h1>
            <a class="btn btn-success" href="${contextPath}/UsuarioControlador?accion=add" style="margin-bottom:10px">Agregar Nuevo Usuario</a>
            <table id="table" class="table table-striped table-bordered dt-responsive nowrap rounded" style="width:100%">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nombres</th>
                        <th>Apellidos</th>
                        <th>Nickname</th>
                        <th>Email</th>
                        <th>Mora</th>
                        <th>Fecha Nacimiento</th>
                        <th>Rol</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        UsuarioDAO dao = new UsuarioDAO();
                        List<Usuario> list = dao.listar();
                        Iterator<Usuario> iter = list.iterator();
                        Usuario usr = null;                    
                        while(iter.hasNext()){
                            usr = iter.next();
                    %>
                        <tr>
                            <td><%= usr.getId()%></td>
                            <td><%= usr.getNombre()%></td>
                            <td><%= usr.getApellido()%></td>
                            <td><%= usr.getNickname()%></td>
                            <td><%= usr.getEmail()%></td>
                            <td>$ <%= usr.getMora()%></td>
                            <td><%= usr.getFecha_nacimiento()%></td>
                            <td><%= usr.getRol()%></td>
                            <td>
                                <a class="btn btn-sm btn-info" href="${contextPath}/UsuarioControlador?accion=editar&id=<%= usr.getId()%>">Editar</a>
                                <a class="btn btn-sm btn-danger" href="javascript:eliminar(<%= usr.getId()%>)">Eliminar</a>
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
  alertify.confirm("¿Realmente decea eliminar este Usuario?", function(e){
     if(e){
         location.href="UsuarioControlador?accion=eliminar&id="+ id;
     } 
  });
}
</script>
