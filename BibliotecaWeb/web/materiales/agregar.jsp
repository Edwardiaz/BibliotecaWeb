<%-- 
    Document   : index
    Created on : Nov 30, 2022, 7:58:44 PM
    Author     : Jorge Díaz
--%>

<%@page import="Modelo.Director"%>
<%@page import="ModeloDAO.DirectorDAO"%>
<%@page import="Modelo.Genero"%>
<%@page import="ModeloDAO.GeneroDAO"%>
<%@page import="Modelo.Artista"%>
<%@page import="ModeloDAO.ArtistaDAO"%>
<%@page import="Modelo.Autor"%>
<%@page import="ModeloDAO.AutorDAO"%>
<%@page import="Modelo.TipoMaterial"%>
<%@page import="ModeloDAO.TipoMaterialDAO"%>
<%@page import="ModeloDAO.EditorialDAO"%>
<%@page import="Modelo.Editorial"%>
<%@page import="java.util.Iterator"%>
<%@page import="Modelo.Materiales"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.MaterialesDAO"%>
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
        <h1>Creacion de Nuevo Material</h1>
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
                <form action="MaterialControlador">
                    <div class="row">
                        <div class="col-6 form-group">
                            <label for="exampleFormControlSelect1">Seleccione tipo de material a guardar</label>
                            <select name="tipoMaterial" onchange="changeInput(this.value);" class="form-control" id="exampleFormControlSelect1">
                                <option value=""> -Seleccione una opción del listado- </option>
                                <%
                                    TipoMaterialDAO tipoDao = new TipoMaterialDAO();
                                    List<TipoMaterial> list1 = tipoDao.listar();
                                    Iterator<TipoMaterial> iter1 = list1.iterator();
                                    TipoMaterial tipo = null;
                                    while(iter1.hasNext()){
                                        tipo = iter1.next();
                                %>
                                <option value="<%= tipo.getId() %>"><%= tipo.getTipoMaterial()%></option>
                                <%}%>
                            </select><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-6 form-group" id="tituloDiv" style="visibility: hidden;">
                            <label>Titulo</label>
                            <input type="text" name="titulo" class="form-control" value="${materiales.titulo}" placeholder="Ingrese titulo del material"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>
                        <!--<c :if test="$ {material.tipoMaterial != 'Libro'}">-->
                        <div class="col-6 form-group" id="autorDiv" style="visibility: hidden;">
                            <label for="exampleFormControlSelect2">Seleccione un Autor</label>
                            <select name="Autor" class="form-control" id="exampleFormControlSelect2" value="${materiales.autor}">
                                <option value=""> -Seleccione una opción del listado- </option>
                                <%
                                    AutorDAO autorDao = new AutorDAO();
                                    List<Autor> list2 = autorDao.listar();
                                    Iterator<Autor> iter2 = list2.iterator();
                                    Autor autor = null;
                                    while(iter2.hasNext()){
                                        autor = iter2.next();
                                %>
                                <option value="<%= autor.getId() %>"><%= autor.getNombre_autor()%></option>
                                <%}%>
                            </select><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>
                        <!--< /c:if> -->
                    </div>
                    <div class="row">
                        <div class="col-6 form-group" id="numPagDiv" style="visibility: hidden;">
                            <label>Número de paginas</label>
                            <input type="text" name="numero_de_paginas" class="form-control" value="${materiales.numero_de_paginas}" placeholder="Ingrese el número de páginas"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>
                        <div class="col-6 form-group" id="editorialDiv" style="visibility: hidden;">
                            <label for="exampleFormControlSelect3">Seleccione editorial</label>
                            <select name="editoriales" class="form-control" id="exampleFormControlSelect3" value="${materiales.editorial}">
                                <option value=""> -Seleccione una opción del listado- </option>
                                <%
                                    EditorialDAO editorialdao = new EditorialDAO();
                                    List<Editorial> list3 = editorialdao.listar();
                                    Iterator<Editorial> iter3 = list3.iterator();
                                    Editorial editorial = null;
                                    while(iter3.hasNext()){
                                        editorial = iter3.next();
                                %>
                                <option value="<%= editorial.getId() %>"><%= editorial.getNombre_editorial()%></option>
                                <%}%>
                            </select><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-6 form-group" id="fechaDiv" style="visibility: hidden;">
                            <label>Fecha de publicación</label>
                            <input type="date" name="Fecha" class="form-control" value="${materiales.fecha_publicacion}" placeholder="Ingrese fecha de publicación"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>                        
                        <div class="col-6 form-group" id="isbnDiv" style="visibility: hidden;">
                            <label>ISBN</label>
                            <input type="text" name="isbn" class="form-control" value="${materiales.isbn}" placeholder="Ingrese el ISBN"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>
                    </div>                     
                    <div class="row">
                        <div class="col-6 form-group" id="periodicidadDiv" style="visibility: hidden;">
                            <label>Periodicidad</label>
                            <input type="text" name="periodicidad" class="form-control" placeholder="Ingrese periodicidad el material"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>                        
                        <div class="col-6 form-group" id="artistaDiv" style="visibility: hidden;">
                            <label for="exampleFormControlSelect4">Seleccione Artista</label>
                            <select name="artistas" class="form-control" id="exampleFormControlSelect4" value="${materiales.artista}">
                                <option value=""> -Seleccione una opción del listado- </option>
                                <%
                                    ArtistaDAO artistaDao = new ArtistaDAO();
                                    List<Artista> list4 = artistaDao.listar();
                                    Iterator<Artista> iter4 = list4.iterator();
                                    Artista artista = null;
                                    while(iter4.hasNext()){
                                        artista = iter4.next();
                                %>
                                <option value="<%= artista.getId() %>"><%= artista.getNombre_artista()%></option>
                                <%}%>
                            </select><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>
                    </div>
                    <div class="row">                     
                        <div class="col-6 form-group" id="generoDiv" style="visibility: hidden;">
                            <label for="exampleFormControlSelect5">Seleccione un Género</label>
                            <select name="generos" class="form-control" id="exampleFormControlSelect5" value="${materiales.genero}">
                                <option value=""> -Seleccione una opción del listado- </option>
                                <%
                                    GeneroDAO generoDao = new GeneroDAO();
                                    List<Genero> list5 = generoDao.listar();
                                    Iterator<Genero> iter5 = list5.iterator();
                                    Genero genero = null;
                                    while(iter5.hasNext()){
                                        genero = iter5.next();
                                %>
                                <option value="<%= genero.getId() %>"><%= genero.getNombre_genero()%></option>
                                <%}%>
                            </select><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>
                        <div class="col-6 form-group" id="duracionDiv" style="visibility: hidden;">
                            <label>Duración</label>
                            <input type="text" name="duracion" class="form-control" placeholder="Ingrese la duración (en minutos)"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>   
                    </div>
                    <div class="row">
                        <div class="col-6 form-group" id="numCancionesDiv" style="visibility: hidden;">
                            <label>Número de canciones</label>
                            <input type="text" name="numero_de_canciones" class="form-control" placeholder="Ingrese número de canciones"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>                        
                        <div class="col-6 form-group" id="directorDiv" style="visibility: hidden;">
                            <label for="exampleFormControlSelect6">Seleccione Director</label>
                            <select name="directores" class="form-control" id="exampleFormControlSelect6" value="${materiales.director}">
                                <option value=""> -Seleccione una opción del listado- </option>
                                <%
                                    DirectorDAO directorDao = new DirectorDAO();
                                    List<Director> list6 = directorDao.listar();
                                    Iterator<Director> iter6 = list6.iterator();
                                    Director director = null;
                                    while(iter6.hasNext()){
                                        director = iter6.next();
                                %>
                                <option value="<%= director.getId() %>"><%= director.getNombre_director()%></option>
                                <%}%>
                            </select><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-6 form-group" id="ubicacionDiv" style="visibility: hidden;">
                            <label>Ubicación</label>
                            <input type="text" name="ubicacion" class="form-control" value="${materiales.ubicacion}" placeholder="Ingrese la ubicación"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>                        
                        <div class="col-6 form-group" id="tesisDiv" style="visibility: hidden;">
                            <label>Autor de Tesis</label>
                            <input type="text" name="nombre_autor_cv" class="form-control" value="${materiales.nombre_autor_cv}" placeholder="Ingrese el autor de la tesis"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-6 form-group" id="unidadDiv" style="visibility: hidden;">
                            <label>Unidades disponibles</label>
                            <input type="text" name="unidades_disponibles" class="form-control" value="${materiales.unidades_disponibles}" placeholder="Ingrese las unidades disponibles"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>                        
                    </div>
                    <div class="row col-12 mr-0 pr-0">
                        <div class="col-6  form-group text-left pl-0 ml-0">
                            <a class="btn btn-info w-50" href="${contextPath}/MaterialControlador?accion=listar">Regresar a Lista</a>
                        </div>
                        <div class="col-6  form-group text-right pr-0 mr-0">
                            <input id="boton" type="submit" class="btn btn-success w-50" name="accion" value="--">
                        </div>                                
                    </div>                              
                </form>           
            </div>
        </div>
    </body>
    <script type="text/javascript">
        document.getElementById('exampleFormControlSelect1').onchange = e => {
            
            let hiddenElement0 = document.getElementById('tituloDiv')
            if(e.target.value !== ''){
                hiddenElement0.style.visibility = 'visible'
            } else {
                hiddenElement0.style.visibility = 'hidden'
            }
            
            let hiddenElement1 = document.getElementById('autorDiv')
            if(e.target.value === '4' || e.target.value === '5'){
                hiddenElement1.style.visibility = 'visible'
            } else {
                hiddenElement1.style.visibility = 'hidden'
            }
            
            let hiddenElement2 = document.getElementById('periodicidadDiv')
            if(e.target.value === '3'){
                hiddenElement2.style.visibility = 'visible'
            } else {
                hiddenElement2.style.visibility = 'hidden'
            }
            
            let hiddenElement3 = document.getElementById('periodicidadDiv')
            if(e.target.value === '3'){
                hiddenElement3.style.visibility = 'visible'
            } else {
                hiddenElement3.style.visibility = 'hidden'
            }
            
            let hiddenElement4 = document.getElementById('numPagDiv')
            if(e.target.value === '4' || e.target.value === '5'){
                hiddenElement4.style.visibility = 'visible'
            } else {
                hiddenElement4.style.visibility = 'hidden'
            }
            
            let hiddenElement5 = document.getElementById('editorialDiv')
            if(e.target.value === '4' || e.target.value === '5'){
                hiddenElement5.style.visibility = 'visible'
            } else {
                hiddenElement5.style.visibility = 'hidden'
            }
            
            let hiddenElement6 = document.getElementById('fechaDiv')
            if(e.target.value === '4' || e.target.value === '5'){
                hiddenElement6.style.visibility = 'visible'
            } else {
                hiddenElement6.style.visibility = 'hidden'
            }
            
            let hiddenElement7 = document.getElementById('isbnDiv')
            if(e.target.value === '4' || e.target.value === '5'){
                hiddenElement7.style.visibility = 'visible'
            } else {
                hiddenElement7.style.visibility = 'hidden'
            }
            
            let hiddenElement8 = document.getElementById('ubicacionDiv')
            if(e.target.value !== ''){
                hiddenElement8.style.visibility = 'visible'
            } else {
                hiddenElement8.style.visibility = 'hidden'
            }
            
            let hiddenElement9 = document.getElementById('unidadDiv')
            if(e.target.value !== ''){
                hiddenElement9.style.visibility = 'visible'
            } else {
                hiddenElement9.style.visibility = 'hidden'
            }
        }
        
        function changeInput(val) {
            var input = document.getElementById("boton");
            if(val.localeCompare('1')){
                input.value = "AgregarCD";
            } else if(val.localeCompare('2')){
                input.value = "AgregarDVD";
            } else if(val.localeCompare('3')){
                input.value = "AgregarRevista";
            } else if(val.localeCompare('4')){
                input.value = "AgregarLibro";
            } else if(val.localeCompare('5')){
                input.value = "AgregarLibro";
            } else if(val.localeCompare('6')){
                input.value = "AgregarTesis";
            }
        }  
    </script>
</html>