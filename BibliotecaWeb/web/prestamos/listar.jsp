<%-- 
    Document   : listar
    Created on : 12-03-2022, 09:43:58 PM
    Author     : Erick Alas
--%>

<%@page import="java.util.Iterator"%>
<%@page import="Modelo.Prestamos"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.PrestamoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file='/assets.jsp' %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Biblioteca Web - Grupo 2</title>
    </head>
    <body>
        <jsp:include page="/menu.jsp"/>
        <div class="container pt-5">
            <h1>Listado de Préstamos activos</h1>

            <table id="table" class="table table-striped table-bordered dt-responsive nowrap rounded" style="width:100%">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Material</th>
                        <th>Usuario</th>
                        <th>Fecha de préstamo</th>
                        <th>Fecha de devolución</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        PrestamoDAO dao = new PrestamoDAO();
                        List<Prestamos> list = dao.listar();
                        Iterator<Prestamos> iter = list.iterator();
                        Prestamos pres = null;
                        while (iter.hasNext()) {
                            pres = iter.next();

                    %>
                    <tr>
                        <td><%= pres.getId()%></td>
                        <td><%= pres.getCodigoMaterial()%></td>
                        <td><%= pres.getUsuario()%></td>
                        <td><%= pres.getFechaPrestamo()%></td>
                        <td><%= pres.getFechaDevolucion()%></td>
                        <td>

                            <a class="btn btn-sm btn-info" data-toggle="modal" data-target="#exampleModalCenter_<%= pres.getId()%>" href="${contextPath}/PrestamoControlador?u=">Detalles</a>
                            <a class="btn btn-sm btn-danger" href="javascript:devolver('<%= pres.getCodigoMaterial() %>','<%= pres.getCodigoUsuario()%>')">Devolver</a>
                    </tr>

                    <!-- Modal -->
                <div class="modal fade" id="exampleModalCenter_<%= pres.getId()%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLongTitle">Detalles de préstamo</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">

                                <label>Nombre de material: <%= pres.getMaterial()%>.</label>
                                <br>
                                <label>Nickname de usuario: <%= pres.getUsuario()%>.</label>
                                <br>
                                <label>Fecha de préstamo: <%= pres.getFechaPrestamo()%>.</label>
                                <br>
                                <label>Fecha de devolución: <%= pres.getFechaDevolucion()%>.</label>
                                <br>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>

                            </div>
                        </div>
                    </div>
                </div>


                <%}%>
                </tbody>
            </table>
            <a class="btn btn-success" href="${contextPath}/PrestamoControlador?accion=add" style="margin-bottom:10px">Nuevo Préstamo</a>
        </div>
    </body>
</html>

<script>
    $(document).ready(function () {
        $('#table').DataTable();
    });

    <c:if test="${not empty exito}">
    alertify.success('${exito}');
        <c:set var="exito" value="" scope="session" />
    </c:if>
    <c:if test="${not empty error}">
    alertify.error('${error}');
        <c:set var="error" value="" scope="session" />
    </c:if>

    function devolver(materialId, userId) {
        alertify.confirm("¿Realmente desea devolver este material?", function (e) {
            if (e) {
                location.href = "PrestamoControlador?accion=devolver&materialId=" + materialId + "&userId=" + userId;
            }
        });
    }
</script>