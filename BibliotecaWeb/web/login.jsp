<%-- 
    Document   : login
    Created on : Dec 3, 2022, 8:46:49 PM
    Author     : Eduardo Trujillo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <head>
    	<meta charset="ISO-8859-1">
        <title>Sistema Biblioteca Web</title>
        <%@ include file='/assets.jsp' %>
    </head>
    </head>
    <body>
        <jsp:include page="/menu.jsp"/>
        <div class="container-fluid">
            <h3>Ingrese al Sistema</h3>
            <div class="row">
                <form id="form_login" action="LoginControlador">
                    <c:if test="${not empty listaErrores}">
                    <div class="alert alert-danger">
                        <ul>
                            <c:forEach var="errores"  items="${requestScope.listaErrores}">
                                <li>${errores}</li>
                            </c:forEach>
                        </ul>
                    </div>
                    </c:if>
                    
                    <div class="form-group">
                      <label for="txtNickname">Nickname</label>
                      <input type="text" name="txtNickname" class="form-control" id="txtNickname" placeholder="Ingrese nickname">
                    </div>
                    <div class="form-group">
                      <label for="txtPass">Contraseña</label>
                      <input type="password" name="txtPass" class="form-control" id="txtPass" placeholder="Ingrese contraseña">
                    </div>
                    <input type="submit" class="btn btn-success w-100" name="accion" value="Ingresar">
                    <div id="formFooter">
                      <a class="underlineHover" data-toggle="modal" data-target="#exampleModalCenter" href="$">Olvido su contraseña?</a>
                    </div>
                    <!-- Modal -->
                    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLongTitle">Restablecer contraseña</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <c:if test="${not empty listaErrores}">
                                    <div class="alert alert-danger">
                                        <ul>
                                            <c:forEach var="errores"  items="${requestScope.listaErrores}">
                                                <li>${errores}</li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                    </c:if>                    
                                    <div class="form-group">
                                      <label for="txtNickname2">Nickname</label>
                                      <input type="text" name="txtNickname2" class="form-control" id="txtNickname2" placeholder="Ingrese nickname">
                                    </div>
                                    <div class="form-group">
                                      <label for="txtEmail2">Email</label>
                                      <input type="email" name="txtEmail2" class="form-control" id="txtEmail2" placeholder="Ingrese email">
                                    </div>
                                    <div class="form-group">
                                        <label>Fecha de Nacimiento(Mes/Día/Año)</label>
                                        <input type="date" name="txtFechaNacimiento2" class="form-control" placeholder="Ingrese fecha de nacimiento de usuario"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                    </div>
                                    <div class="form-group">
                                        <label>Nueva Contraseña</label>
                                        <input type="password" name="txtNewPass" class="form-control" placeholder="Ingrese nuevacontraseña"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                    </div>                    
                                </div>
                                <div class="modal-footer">
                                    <input type="submit" class="btn btn-success w-50" name="accion" value="Restablecer">
                                </div>
                            </div>
                        </div>
                    </div>                    
                </form>                  
            </div>
        </div>
    </body>
    
                    <!-- Modal -->
    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Restablecer contraseña</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <c:if test="${not empty listaErrores}">
                    <div class="alert alert-danger">
                        <ul>
                            <c:forEach var="errores"  items="${requestScope.listaErrores}">
                                <li>${errores}</li>
                            </c:forEach>
                        </ul>
                    </div>
                    </c:if>                    
                    <div class="form-group">
                      <label for="txtNickname2">Nickname</label>
                      <input type="text" name="txtNickname2" class="form-control" id="txtNickname2" placeholder="Ingrese nickname">
                    </div>
                    <div class="form-group">
                      <label for="txtEmail2">Email</label>
                      <input type="email" name="txtEmail2" class="form-control" id="txtEmail2" placeholder="Ingrese email">
                    </div>
                    <div class="form-group">
                        <label>Fecha de Nacimiento(Mes/Día/Año)</label>
                        <input type="date" name="txtFechaNacimiento2" class="form-control" placeholder="Ingrese fecha de nacimiento de usuario"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                    <div class="form-group">
                        <label>Nueva Contraseña</label>
                        <input type="password" name="txtNewPass" class="form-control" placeholder="Ingrese nuevacontraseña"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>                    
                </div>
                <div class="modal-footer">
                    <input type="submit" class="btn btn-success w-50" name="accion" value="Restablecer">
                </div>
            </div>
        </div>
    </div>    
</html>

<script type="text/javascript">
    <c:if test="${not empty exito}">
        alertify.success('${exito}');
       <c:set var="exito" value="" scope="session" />
    </c:if>
    
    <c:if test="${not empty error}">
        alertify.error('${error}');
        <c:set var="error" value="" scope="session" />
    </c:if>
</script>
