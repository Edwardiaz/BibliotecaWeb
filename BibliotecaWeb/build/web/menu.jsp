<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">Biblioteca Web</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="${contextPath}/index.jsp">Inicio <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Mantenimiento
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="${contextPath}/UsuarioControlador?accion=listar">Usuarios</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="${contextPath}/RolControlador?accion=listar">Roles</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="${contextPath}/ArtistaControlador?accion=listar">Artistas</a>
          <a class="dropdown-item" href="${contextPath}/AutorControlador?accion=listar">Autores</a>
          <a class="dropdown-item" href="${contextPath}/DirectorControlador?accion=listar">Directores</a>
          <a class="dropdown-item" href="${contextPath}/EditorialControlador?accion=listar">Editoriales</a>
          <a class="dropdown-item" href="${contextPath}/GeneroControlador?accion=listar">Generos</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Prestamos
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="${contextPath}/Prestamo?accion=listar">Listar</a>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Consultas</a>
      </li>
    </ul>
        <ul class="navbar-nav mr-0">
            <li class="nav-item">
              <a class="nav-link" href="#">Registrarse</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Ingresar</a>
            </li>            
        </ul>
  </div>
</nav>
        

