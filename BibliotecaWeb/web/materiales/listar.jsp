<%-- 
    Document   : listar
    Created on : Dec 3, 2022, 10:20:14 PM
    Author     : Jorge Díaz
--%>

<%@page import="java.util.Iterator"%>
<%@page import="Modelo.Materiales"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.MaterialesDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file='/assets.jsp' %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Materiales</title>
    </head>
    <body>
        <jsp:include page="/menu.jsp"/>
        <div class="container-fluid">
            <h1>Listado de Materiales del Sistema</h1>
            <a class="btn btn-success" href="${contextPath}/MaterialControlador?accion=add" style="margin-bottom:10px">Agregar Nuevo Material</a>
            <table id="table" class="table table-striped table-bordered dt-responsive nowrap rounded" style="width:100%">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Titulo</th>
                        <th>Tipo Material</th>
                        <th>Autor</th>
                        <th># paginas</th>
                        <th>Editorial</th>
                        <th>ISBN</th>
                        <th>Periodicidad</th>
                        <th>Fecha Publicación</th>
                        <th>Artista</th>
                        <th>Genero</th>
                        <th>Duración</th>
                        <th># de Canciones</th>
                        <th>Director</th>
                        <th>Ubicación</th>
                        <th>Autor Tesis</th>
                        <th>Unidades disponibles</th>
                        <th>Acciones</th>
                        <th>Estado</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        MaterialesDAO dao = new MaterialesDAO();
                        List<Materiales> list = dao.listar();
                        Iterator<Materiales> iter = list.iterator();
                        Materiales mat = null;                    
                        while(iter.hasNext()){
                            mat = iter.next();
                    %>
                        <tr>
                            <td><%= mat.getId()%></td>
                            <td><%= mat.getTitulo()%></td>
                            <td><%= mat.getTipoMaterial()%></td>
                            <td><%= mat.getAutor()%></td>
                            <td><%= mat.getNumeroDePaginas()%></td>
                            <td><%= mat.getEditorial()%></td>
                            <td><%= mat.getIsbn()%></td>
                            <td><%= mat.getPeriodicidad()%></td>
                            <td><%= mat.getFechaPublicacion()%></td>
                            <td><%= mat.getArtista()%></td>
                            <td><%= mat.getGenero()%></td>
                            <td><%= mat.getDuracion()%></td>
                            <td><%= mat.getNumeroDeCanciones()%></td>
                            <td><%= mat.getDirector()%></td>
                            <td><%= mat.getUbicacion()%></td>
                            <td><%= mat.getNombre_autor_CV()%></td>
                            <td><%= mat.getUnidadesDisponibles()%></td>
                            <td>
                                <a class="btn btn-sm btn-info" href="${contextPath}/MaterialControlador?accion=editar&id=<%= mat.getId()%>">Editar</a>
                                <a class="btn btn-sm btn-danger" href="${contextPath}/MaterialControlador?accion=eliminar&id=<%= mat.getId()%>">Eliminar</a>
                            </td>
                            <td><%= mat.getEstado()%></td>
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
<c:if test="${materiales.getEstado > 0}">

</c:if>
    
function eliminar(id){
  alertify.confirm("¿Realmente decea eliminar este material?", function(e){
     if(e){
         location.href="MaterialControlador?accion=eliminar&id="+ id;
     } 
  });
}
</script>
