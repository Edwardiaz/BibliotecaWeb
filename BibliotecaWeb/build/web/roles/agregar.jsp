<%-- 
    Document   : index
    Created on : Nov 30, 2022, 7:58:44 PM
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
    <head>
    	<meta http-equiv="Content-Type" content="text/html" charset="ISO-8859-1">
        <title>Sistema Biblioteca Web</title>
        <%@ include file='/assets.jsp' %>
    </head>
    </head>
    <body>
        <jsp:include page="/menu.jsp"/>
        <div class="container-fluid">
        <h1>Creacion de Nuevo Rol</h1>
            <div class="col-6">
                <c:if test="${not empty listaErrores}">
                <div class="alert alert-danger">
                    <ul>
                        <c:forEach var="errores"  items="${requestScope.listaErrores}">
                            <li>${errores}</li>
                        </c:forEach>
                    </ul>
                </div>
                </c:if> 
                <c:if test="${not empty existe}">
                <div class="alert alert-danger">
                    ${existe}
                </div>
                </c:if>                 
                <form action="RolControlador">
                    <div class="row">
                        <div class="col-6 form-group">
                            <label>Nombre Rol</label>
                            <input type="text" name="txtRol" class="form-control" value="${rol.rol}" placeholder="Ingrese nombres de rol"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-6 form-group">
                            <label>Numero de Prestamos</label>
                            <input type="text" name="txtNumeroPrestamos" class="form-control" value="0" placeholder="Ingrese numero de prestamos para este rol"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>                        
                    </div>
                    <div class="row">
                        <div class="col-6 form-group">
                            <label>Dias de Prestamo</label>
                            <input type="text" name="txtDiasPrestamo" class="form-control" value="0" placeholder="Ingrese dias  amximo de prestamo"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>
                    </div>
                    <div class="row col-6 mr-0 pr-0">
                        <div class="col-6  form-group text-left pl-0 ml-0">
                            <a class="btn btn-info w-75" href="${contextPath}/RolControlador?accion=listar">Regresar a Lista</a>
                        </div>
                        <div class="col-6  form-group text-right pr-0 mr-0">
                            <input type="submit" class="btn btn-success w-75" name="accion" value="Agregar">
                        </div>                                
                    </div>                              
                </form>           
            </div>
        </div>
    </body>
</html>