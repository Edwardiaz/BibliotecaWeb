<%-- 
    Document   : index
    Created on : Dec 5, 2022, 4:00:12 PM
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
        <h1>Editar informacion del material</h1>
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
                            <select name="tipoMaterial" class="form-control" value="${material.tipoMaterial}" id="exampleFormControlSelect1">
                                <option value=""> ${material.tipoMaterial} </option>
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
                        <div class="col-6 form-group" id="tituloDiv" >
                            <label>Titulo</label>
                            <input type="text" name="titulo" class="form-control" value="${material.titulo}" placeholder="Ingrese titulo del material"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>
                        <!--<c :if test="$ {material.tipoMaterial != 'Libro'}">-->
                        <div class="col-6 form-group" id="autorDiv" >
                            <label for="exampleFormControlSelect2">Seleccione un Autor</label>
                            <select name="Autor" class="form-control" id="exampleFormControlSelect2" value="${material.autor}">
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
                        <div class="col-6 form-group" id="numPagDiv" >
                            <label>Número de paginas</label>
                            <input type="text" name="numero_de_paginas" class="form-control" value="${materiales.numeroPaginas}" placeholder="Ingrese el número de páginas"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>
                        <div class="col-6 form-group" id="editorialDiv" >
                            <label for="exampleFormControlSelect3">Seleccione editorial</label>
                            <select name="editoriales" class="form-control" id="exampleFormControlSelect3" value="${material.editorial}">
                                <option value=""> ${material.editorial} </option>
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
                        <div class="col-6 form-group" id="fechaDiv" >
                            <label>Fecha de publicación</label>
                            <input type="date" name="Fecha" class="form-control" value="${material.fecha}" placeholder="Ingrese fecha de publicación"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>                        
                        <div class="col-6 form-group" id="isbnDiv" >
                            <label>ISBN</label>
                            <input type="text" name="isbn" class="form-control" value="${material.isbn}" placeholder="Ingrese el ISBN"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>
                    </div>                     
                    <div class="row">
                        <div class="col-6 form-group" id="periodicidadDiv" >
                            <label>Periodicidad</label>
                            <input type="text" name="periodicidad" class="form-control" placeholder="Ingrese periodicidad el material"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>                        
                        <div class="col-6 form-group" id="artistaDiv" >
                            <label for="exampleFormControlSelect4">Seleccione Artista</label>
                            <select name="artistas" class="form-control" id="exampleFormControlSelect4" value="${material.artista}">
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
                        <div class="col-6 form-group" id="generoDiv" >
                            <label for="exampleFormControlSelect5">Seleccione un Género</label>
                            <select name="generos" class="form-control" id="exampleFormControlSelect5" value="${material.genero}">
                                <option value=""> ${material.genero} </option>
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
                        <div class="col-6 form-group" id="duracionDiv" >
                            <label>Duración</label>
                            <input type="text" name="duracion" class="form-control" placeholder="Ingrese la duración (en minutos)"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>   
                    </div>
                    <div class="row">
                        <div class="col-6 form-group" id="numCancionesDiv" >
                            <label>Número de canciones</label>
                            <input type="text" name="numero_de_canciones" class="form-control" placeholder="Ingrese número de canciones"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>                        
                        <div class="col-6 form-group" id="directorDiv" >
                            <label for="exampleFormControlSelect6">Seleccione Director</label>
                            <select name="directores" class="form-control" id="exampleFormControlSelect6" value="${material.director}">
                                <option value=""> ${material.director} </option>
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
                        <div class="col-6 form-group" id="ubicacionDiv" >
                            <label>Ubicación</label>
                            <input type="text" name="ubicacion" class="form-control" value="${material.ubicacion}" placeholder="Ingrese la ubicación"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>                        
                        <div class="col-6 form-group" id="tesisDiv" >
                            <label>Autor de Tesis</label>
                            <input type="text" name="nombre_autor_cv" class="form-control" value="${material.nombre_autor_cv}" placeholder="Ingrese el autor de la tesis"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-6 form-group" id="unidadDiv" >
                            <label>Unidades disponibles</label>
                            <input type="text" name="unidades_disponibles" class="form-control" value="${material.unidades_disponibles}" placeholder="Ingrese las unidades disponibles"><span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        </div>                        
                    </div>
                    <div class="row col-12 mr-0 pr-0">
                        <div class="col-6  form-group text-left pl-0 ml-0">
                            <a class="btn btn-info w-50" href="${contextPath}/MaterialControlador?accion=listar">Regresar a Lista</a>
                        </div>
                        <div class="col-6  form-group text-right pr-0 mr-0">
                            <input id="boton" type="submit" class="btn btn-success w-50"  name="accion" value="Actualizar">
                        </div>                                
                    </div>                              
                </form>           
            </div>
        </div>
    </body>
    <script type="text/javascript">
//        document.getElementById('exampleFormControlSelect1').onchange = e => {
//            
//            let hiddenElement0 = document.getElementById('tituloDiv')
//            if(e.target.value !== ''){
//                hiddenElement0.style.visibility = 'visible';
//                hiddenElement0.style.display = 'inline-block';
//            } else {
//                hiddenElement0.style.visibility = 'hidden'
//                hiddenElement0.style.display = 'none';
//            }
//            
//            let hiddenElement1 = document.getElementById('autorDiv')
//            if(e.target.value === '4' || e.target.value === '5'){
//                hiddenElement1.style.visibility = 'visible'
//                hiddenElement1.style.display = 'inline-block';
//            } else {
//                hiddenElement1.style.visibility = 'hidden'
//                hiddenElement1.style.display = 'none';
//            }
//            
//            let hiddenElement2 = document.getElementById('periodicidadDiv')
//            if(e.target.value === '3'){
//                hiddenElement2.style.visibility = 'visible'
//                hiddenElement2.style.display = 'inline-block';
//            } else {
//                hiddenElement2.style.visibility = 'hidden'
//                hiddenElement2.style.display = 'none';
//            }
//            
//            let hiddenElement3 = document.getElementById('directorDiv')
//            if(e.target.value === '2'){
//                hiddenElement3.style.visibility = 'visible'
//                hiddenElement3.style.display = 'inline-block';
//            } else {
//                hiddenElement3.style.visibility = 'hidden'
//                hiddenElement3.style.display = 'none';
//            }
//            
//            let hiddenElement4 = document.getElementById('numPagDiv')
//            if(e.target.value === '4' || e.target.value === '5' || e.target.value === '6'){
//                hiddenElement4.style.visibility = 'visible'
//                hiddenElement4.style.display = 'inline-block';
//            } else {
//                hiddenElement4.style.visibility = 'hidden'
//                hiddenElement4.style.display = 'none';
//            }
//            
//            let hiddenElement5 = document.getElementById('editorialDiv')
//            if(e.target.value === '3' || e.target.value === '4' || e.target.value === '5'){
//                hiddenElement5.style.visibility = 'visible'
//                hiddenElement5.style.display = 'inline-block';
//            } else {
//                hiddenElement5.style.visibility = 'hidden'
//                hiddenElement5.style.display = 'none';
//            }
//            
//            let hiddenElement6 = document.getElementById('fechaDiv')
//            if(e.target.value !== ''){
//                hiddenElement6.style.visibility = 'visible'
//                hiddenElement6.style.display = 'inline-block';
//            } else {
//                hiddenElement6.style.visibility = 'hidden'
//                hiddenElement6.style.display = 'none';
//            }
//            
//            let hiddenElement7 = document.getElementById('isbnDiv')
//            if(e.target.value === '4' || e.target.value === '5'){
//                hiddenElement7.style.visibility = 'visible'
//                hiddenElement7.style.display = 'inline-block';
//            } else {
//                hiddenElement7.style.visibility = 'hidden'
//                hiddenElement7.style.display = 'none';
//            }
//            
//            let hiddenElement8 = document.getElementById('ubicacionDiv')
//            if(e.target.value !== ''){
//                hiddenElement8.style.visibility = 'visible'
//                hiddenElement8.style.display = 'inline-block';
//            } else {
//                hiddenElement8.style.visibility = 'hidden'
//                hiddenElement8.style.display = 'none';
//            }
//            
//            let hiddenElement9 = document.getElementById('unidadDiv')
//            if(e.target.value !== ''){
//                hiddenElement9.style.visibility = 'visible'
//                hiddenElement9.style.display = 'inline-block';
//            } else {
//                hiddenElement9.style.visibility = 'hidden'
//                hiddenElement9.style.display = 'none';
//            }
//            
//            let hiddenElement10 = document.getElementById('tesisDiv')
//            if(e.target.value === '6'){
//                hiddenElement10.style.visibility = 'visible'
//                hiddenElement10.style.display = 'inline-block';
//            } else {
//                hiddenElement10.style.visibility = 'hidden'
//                hiddenElement10.style.display = 'none';
//            }
//            let hiddenElement11 = document.getElementById('artistaDiv')
//            if(e.target.value === '1'){
//                hiddenElement11.style.visibility = 'visible'
//                hiddenElement11.style.display = 'inline-block';
//            } else {
//                hiddenElement11.style.visibility = 'hidden'
//                hiddenElement11.style.display = 'none';
//            }
//            let hiddenElement12 = document.getElementById('generoDiv')
//            if(e.target.value === '1' || e.target.value === '2'){
//                hiddenElement12.style.visibility = 'visible'
//                hiddenElement12.style.display = 'inline-block';
//            } else {
//                hiddenElement12.style.visibility = 'hidden'
//                hiddenElement12.style.display = 'none';
//            }
//            let hiddenElement13 = document.getElementById('duracionDiv')
//            if(e.target.value === '1' || e.target.value === '2'){
//                hiddenElement13.style.visibility = 'visible'
//                hiddenElement13.style.display = 'inline-block';
//            } else {
//                hiddenElement13.style.visibility = 'hidden'
//                hiddenElement13.style.display = 'none';
//            }
//            let hiddenElement14 = document.getElementById('numCancionesDiv')
//            if(e.target.value === '1'){
//                hiddenElement14.style.visibility = 'visible'
//                hiddenElement14.style.display = 'inline-block';
//            } else {
//                hiddenElement14.style.visibility = 'hidden'
//                hiddenElement14.style.display = 'none';
//            }
            
//            var input = document.getElementById("boton");
//            if(e.target.value === "1"){
//                input.value = "AgregarCD";
//                document.getElementById("boton").disabled = false;
//            } else if(e.target.value==="2"){
//                input.value = "AgregarDVD";
//                document.getElementById("boton").disabled = false;
//            } else if(e.target.value === "3"){
//                input.value = "AgregarRevista";
//                document.getElementById("boton").disabled = false;
//            } else if(e.target.value==="4"){
//                input.value = "AgregarLibro";
//                document.getElementById("boton").disabled = false;
//            } else if(e.target.value==="5"){
//                input.value = "AgregarLibro";
//                document.getElementById("boton").disabled = false;
//            } else if(e.target.value==="6"){
//                input.value = "AgregarTesis";
//                document.getElementById("boton").disabled = false;
//            } else {
//                input.value = "--";
//                document.getElementById("boton").disabled = true;
//            }
//        }
    </script>
</html>