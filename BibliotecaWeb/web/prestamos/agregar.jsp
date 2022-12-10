<%-- 
    Document   : agregar
    Created on : 12-06-2022, 08:24:27 PM
    Author     : Erick Alas
--%>

<%@page import="ModeloDAO.UsuarioDAO"%>
<%@page import="Modelo.Usuario"%>
<%@page import="Modelo.Materiales"%>
<%@page import="ModeloDAO.MaterialesDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="ModeloDAO.PrestamoDAO"%>
<%@page import="Modelo.Prestamos"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
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
    <div class="container">
        <h1 class="py-5 text-center" style=" color:#d33333">Creacion de Nuevo Préstamo</h1>
        <div class="col-8">
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


            <form action="PrestamoControlador">

                <div class="row">
                    <div class="col-6 form-group">
                        <label for="exampleFormControlSelect1">Seleccione material</label>
                        <select name="txtMaterial" class="form-control" id="exampleFormControlSelect1" value="${materialId}">
                            <option value=""> - Seleccione un material del listado - </option>
                            <%
                                MaterialesDAO Mdao = new MaterialesDAO();
                                List<Materiales> listM = Mdao.listar();
                                Iterator<Materiales> iterM = listM.iterator();
                                Materiales mat = null;
                                while (iterM.hasNext()) {
                                    mat = iterM.next();

                            %>
                            <option value="<%= mat.getId() %>"><%= mat.getTitulo()%></option>
                            <%}%>
                        </select><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div> 
                        
                                            <div class="col-6 form-group">
                        <label for="exampleFormControlSelect2">Seleccione usuario</label>
                        <select name="txtUsuario" class="form-control" id="exampleFormControlSelect2" value="${userId}">
                            <option value=""> - Seleccione un usuario del listado - </option>
                            <%
                                UsuarioDAO Udao = new UsuarioDAO();
                                List<Usuario> listU = Udao.listar();
                                Iterator<Usuario> iterU = listU.iterator();
                                Usuario user = null;
                                while (iterU.hasNext()) {
                                    user = iterU.next();
                            %>
                            <option value="<%= user.getId()%>"><%= user.getNickname()%></option>



                            <%}%>
                        </select><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>   
                </div>
                                         
   
         
            

                <div class="row col-12 mr-0 pr-0">
                    <div class="col-6  form-group text-left pl-0 ml-0">
                        <a class="btn btn-info w-50" href="${contextPath}/PrestamoControlador?accion=listar">Regresar a Lista</a>
                    </div>
                    <div class="col-6  form-group text-right pr-0 mr-0">
                        <input type="submit" class="btn btn-success w-50" name="accion" value="Agregar">
                    </div>                                
                </div>                              
            </form>           
        </div>
    </div>
</body>
</html>
