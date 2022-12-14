<%-- 
    Document   : listar
    Created on : Nov 30, 2022, 8:02:34 PM
    Author     : Eduardo Trujillo
--%>

<%@page import="java.util.Iterator"%>
<%@page import="Modelo.Artista"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.ArtistaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file='/assets.jsp' %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Artistas</title>
    </head>
    <body>
        <jsp:include page="/menu.jsp"/>
        <div class="container-fluid">
            <h1>Listado de Artistas</h1>
            <a class="btn btn-success" href="${contextPath}/ArtistaControlador?accion=add" style="margin-bottom:10px">Agregar Nuevo Artista</a>
            <table id="table" class="table table-striped table-bordered dt-responsive nowrap rounded" style="width:100%">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nombre de Artista</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArtistaDAO dao = new ArtistaDAO();
                        List<Artista> list = dao.listar();
                        Iterator<Artista> iter = list.iterator();
                        Artista artista = null;                    
                        while(iter.hasNext()){
                            artista = iter.next();
                    %>
                        <tr>
                            <td><%= artista.getId()%></td>
                            <td><%= artista.getNombre_artista()%></td>
                            <td>
                                <a class="btn btn-sm btn-info" href="${contextPath}/ArtistaControlador?accion=editar&id=<%= artista.getId()%>">Editar</a>
                                <a class="btn btn-sm btn-danger" href="javascript:eliminar(<%= artista.getId()%>)">Eliminar</a>
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
  alertify.confirm("??Realmente desea eliminar este Artista?", function(e){
     if(e){
         location.href="ArtistaControlador?accion=eliminar&id="+ id;
     } 
  });
}
</script>
