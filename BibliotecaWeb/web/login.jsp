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
                </form>                  
            </div>
        </div>
    </body>
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
