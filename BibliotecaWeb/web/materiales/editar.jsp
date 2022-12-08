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
        <h1>Editar Usuario</h1>
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
                <form action="UsuarioControlador">
                    <div class="row">
                        <div class="col-6 form-group">
                            <label>Nombres</label>
                            <input type="text" name="txtNombre" class="form-control" value="${usuario.nombre}" placeholder="Ingrese nombres de usuario"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>
                        <div class="col-6 form-group">
                            <label>Apellidos</label>
                            <input type="text" name="txtApellido" class="form-control" value="${usuario.apellido}" placeholder="Ingrese apellidos de usuario"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>                        
                    </div>
                    <div class="row">
                        <div class="col-6 form-group">
                            <label>Nickname</label>
                            <input type="text" name="txtNickname" class="form-control" value="${usuario.nickname}" placeholder="Ingrese nickname de usuario"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>
                        <div class="col-6 form-group">
                            <label>Email</label>
                            <input type="email" name="txtEmail" class="form-control" value="${usuario.email}" placeholder="Ingrese email de usuario"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>                        
                    </div>
                    <div class="row">
                        <div class="col-6 form-group">
                            <label>Fecha de Nacimiento</label>
                            <input type="date" name="txtFechaNacimiento" class="form-control" value="${usuario.fecha_nacimiento }" placeholder="Ingrese fecha de nacimiento de usuario"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>                        
                        <div class="col-6 form-group">
                            <label>Contraseña</label>
                            <input type="password" name="txtPass" class="form-control" value="${usuario.pass}" placeholder="Ingrese contraseña"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>
                    </div>                     
                    <div class="row">
                        <div class="col-6 form-group">
                            <label>Mora</label>
                            <input type="currency" name="txtMora" class="form-control" placeholder="Ingrese mora de usuario" value="0.0"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>                        
                        <div class="col-6 form-group">
                            <label for="exampleFormControlSelect1">Seleccione rol</label>
                            <select name="txtRol" class="form-control" id="exampleFormControlSelect1" value="${usuario.rol}">
                                <option value="${usuario.codigo_rol}">${usuario.rol}</option>
                                <%
                                    RolDAO dao = new RolDAO();
                                    List<Rol> list = dao.listar();
                                    Iterator<Rol> iter = list.iterator();
                                    Rol rol = null;
                                    while(iter.hasNext()){
                                        rol = iter.next();
                                %>
                                <option value="<%= rol.getId() %>"><%= rol.getRol() %></option>
                                <%}%>
                            </select><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>                            
                    </div>
                        <div class="row col-12 mr-0 pr-0">
                            <div class="col-6  form-group text-left pl-0 ml-0">
                                <a class="btn btn-info w-50" href="${contextPath}/UsuarioControlador?accion=listar">Regresar a Lista</a>
                            </div>
                            <div class="col-6  form-group text-right pr-0 mr-0">
                                <input type="submit" class="btn btn-success w-50" name="accion" value="Actualizar">
                            </div>                                
                        </div>                              
                </form>           
            </div>
        </div>
    </body>
</html>