/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Materiales;
import ModeloDAO.MaterialesDAO;
import Utils.Validaciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jorge Díaz
 */
public class MaterialControlador extends HttpServlet{
    String listar = "materiales/listar.jsp";
    String agregar = "materiales/agregar.jsp";
    String agregarDVD = "materiales/agregarDVD.jsp";
    String agregarRev = "materiales/agregarRevista.jsp";
    String agregarObra = "materiales/agregarObra.jsp";
    String agregarCDA = "materiales/agregarCDA.jsp";
    String agregarTesis = "materiales/agregarTesis.jsp";
    String editar = "materiales/editar.jsp";
    String eliminar = "materiales/eliminar.jsp";
    
    Materiales material = new Materiales();
    MaterialesDAO materialDao = new MaterialesDAO();
    
    ArrayList<String> listaErrores = new ArrayList<>();
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MaterialControlador</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MaterialControlador at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso = "";
        String action = request.getParameter("accion");
        if(action.equalsIgnoreCase("listar")){
            acceso = listar;
            RequestDispatcher vista = request.getRequestDispatcher(acceso);
            vista.forward(request, response);            
        }else if(action.equals("add")){
            acceso = agregar;
            RequestDispatcher vista = request.getRequestDispatcher(acceso);
            vista.forward(request, response);            
        }else if(action.equals("Agregar")){
            try {
                listaErrores.clear();
                
                //material.setId(request.getParameter("id"));
                material.setTitulo(request.getParameter("titulo"));
                material.setTipoMaterial(request.getParameter("tipo_material"));
                material.setAutor(request.getParameter("Autor"));
                material.setNumeroDePaginas(request.getParameter("numero_de_paginas"));
                material.setEditorial(request.getParameter("editoriales"));
                material.setIsbn(request.getParameter("isbn"));
                material.setPeriodicidad(request.getParameter("periodicidad"));
                material.setFechaPublicacion(request.getParameter("fecha"));
                material.setArtista(request.getParameter("artistas"));
                material.setGenero(request.getParameter("generos"));
                material.setDuracion(request.getParameter("duracion"));
                material.setNumeroDeCanciones(request.getParameter("numero_de_canciones"));
                material.setDirector(request.getParameter("directores"));
                material.setUbicacion(request.getParameter("ubicacion"));
                material.setNombre_autor_CV(request.getParameter("nombre_autor_cv"));
                material.setUnidadesDisponibles(Integer.parseInt(request.getParameter("unidades_disponibles")));

                if (Validaciones.isEmpty(material.getTitulo())) {
                    listaErrores.add("Campo Titulo obligatorio");
                }            
                if (Validaciones.isEmpty(material.getTipoMaterial())) {
                    listaErrores.add("Campo Tipo Material es obligatorio");
                }
                if (Validaciones.isEmpty(material.getAutor())) {
                    listaErrores.add("Campo Autor es obligatorio");
                }
                if (Validaciones.isEmpty(material.getNumeroDePaginas())) {
                    listaErrores.add("Campo numero de páginas es obligatorio");
                }
                if (Validaciones.isEmpty(material.getEditorial())) {
                    listaErrores.add("Campo Editorial es obligatorio");
                }
                if (Validaciones.isEmpty(material.getIsbn())) {
                    listaErrores.add("Campo ISBN es obligatorio");
                }
                if (Validaciones.isEmpty(material.getPeriodicidad())) {
                    listaErrores.add("Campo Periodicidad es obligatorio");
                }
                if (Validaciones.isEmpty(material.getFechaPublicacion().toString())) {
                    listaErrores.add("Campo Fecha Publicación es obligatorio");
                }
                if (Validaciones.isEmpty(material.getArtista())) {
                    listaErrores.add("Campo Artista es obligatorio");
                }
                if (Validaciones.isEmpty(material.getGenero())) {
                    listaErrores.add("Campo Genero es obligatorio");
                }
                if (Validaciones.isEmpty(material.getDuracion())) {
                    listaErrores.add("Campo Duración es obligatorio");
                }
                if (Validaciones.isEmpty(material.getNumeroDeCanciones())) {
                    listaErrores.add("Campo Numero de Canciones es obligatorio");
                }
                if (Validaciones.isEmpty(material.getDirector())) {
                    listaErrores.add("Campo Director es obligatorio");
                }
                if (Validaciones.isEmpty(material.getUbicacion())) {
                    listaErrores.add("Campo Ubicación es obligatorio");
                }
                if (Validaciones.isEmpty(material.getNombre_autor_CV())) {
                    listaErrores.add("Campo Autor de CV es obligatorio");
                }
                if (Validaciones.isEmpty(material.getUnidadesDisponibles().toString())) {
                    listaErrores.add("Campo Unidades Disponibles es obligatorio");
                }
                
                if (listaErrores.size() > 0) {
                    request.setAttribute("listaErrores", listaErrores);
                    request.setAttribute("Material", material);
                    acceso = agregar;
                    RequestDispatcher vista = request.getRequestDispatcher(acceso);
                    vista.forward(request, response);                    
                } else if (materialDao.materialExiste(material.getTitulo())) {
                    request.getSession().setAttribute("existe", "¡El material ya existe!");
                    request.setAttribute("material", material);
                    acceso = agregar;
                    RequestDispatcher vista = request.getRequestDispatcher(acceso);
                    vista.forward(request, response);                    
                } else {
                    if (materialDao.agregar(material) > 0) {
                        request.getSession().setAttribute("exito", "Usuario agregado exitosamente!");
                        response.sendRedirect(request.getContextPath() + "/UsuarioControlador?accion=listar");
                        //acceso = listar;
                    } else {
                        request.getSession().setAttribute("error", "El usuario no ha podido ser agregado!");
                        response.sendRedirect(request.getContextPath() + "/UsuarioControlador?accion=listar");
                        //acceso = listar;
                    }
                }
            } catch (SQLException ex) {
               Logger.getLogger(MaterialControlador.class.getName()).log(Level.SEVERE, null, ex);
           }
        }else if(action.equals("AgregarLibro")){
            try {
                listaErrores.clear();
                
                //material.setId(request.getParameter("id"));
                material.setTitulo(request.getParameter("titulo"));
                //material.setTipoMaterial(request.getParameter("tipo_material"));
                material.setAutor(request.getParameter("Autor"));
                material.setNumeroDePaginas(request.getParameter("numero_de_paginas"));
                material.setEditorial(request.getParameter("editoriales"));
                material.setIsbn(request.getParameter("isbn"));
                //material.setPeriodicidad(request.getParameter("periodicidad"));
                material.setFechaPublicacion(request.getParameter("fecha"));
                //material.setArtista(request.getParameter("artistas"));
                //material.setGenero(request.getParameter("generos"));
                //material.setDuracion(request.getParameter("duracion"));
                //material.setNumeroDeCanciones(request.getParameter("numero_de_canciones"));
                //material.setDirector(request.getParameter("directores"));
                material.setUbicacion(request.getParameter("ubicacion"));
                //material.setNombre_autor_CV(request.getParameter("nombre_autor_cv"));
                material.setUnidadesDisponibles(Integer.parseInt(request.getParameter("unidades_disponibles")));

                if (Validaciones.isEmpty(material.getTitulo())) {
                    listaErrores.add("Campo Titulo obligatorio");
                }            
//                if (Validaciones.isEmpty(material.getTipoMaterial())) {
//                    listaErrores.add("Campo Tipo Material es obligatorio");
//                }
                if (Validaciones.isEmpty(material.getAutor())) {
                    listaErrores.add("Campo Autor es obligatorio");
                }
                if (Validaciones.isEmpty(material.getNumeroDePaginas())) {
                    listaErrores.add("Campo numero de páginas es obligatorio");
                }
                if (Validaciones.isEmpty(material.getEditorial())) {
                    listaErrores.add("Campo Editorial es obligatorio");
                }
                if (Validaciones.isEmpty(material.getIsbn())) {
                    listaErrores.add("Campo ISBN es obligatorio");
                }
//                if (Validaciones.isEmpty(material.getPeriodicidad())) {
//                    listaErrores.add("Campo Periodicidad es obligatorio");
//                }
                if (Validaciones.isEmpty(material.getFechaPublicacion().toString())) {
                    listaErrores.add("Campo Fecha Publicación es obligatorio");
                }
//                if (Validaciones.isEmpty(material.getArtista())) {
//                    listaErrores.add("Campo Artista es obligatorio");
//                }
//                if (Validaciones.isEmpty(material.getGenero())) {
//                    listaErrores.add("Campo Genero es obligatorio");
//                }
//                if (Validaciones.isEmpty(material.getDuracion())) {
//                    listaErrores.add("Campo Duración es obligatorio");
//                }
//                if (Validaciones.isEmpty(material.getNumeroDeCanciones())) {
//                    listaErrores.add("Campo Numero de Canciones es obligatorio");
//                }
//                if (Validaciones.isEmpty(material.getDirector())) {
//                    listaErrores.add("Campo Director es obligatorio");
//                }
                if (Validaciones.isEmpty(material.getUbicacion())) {
                    listaErrores.add("Campo Ubicación es obligatorio");
                }
//                if (Validaciones.isEmpty(material.getNombre_autor_CV())) {
//                    listaErrores.add("Campo Autor de CV es obligatorio");
//                }
                if (Validaciones.isEmpty(material.getUnidadesDisponibles().toString())) {
                    listaErrores.add("Campo Unidades Disponibles es obligatorio");
                }
                
                if (listaErrores.size() > 0) {
                    request.setAttribute("listaErrores", listaErrores);
                    request.setAttribute("Material", material);
                    acceso = agregar;
                    RequestDispatcher vista = request.getRequestDispatcher(acceso);
                    vista.forward(request, response);                    
                } else if (materialDao.materialExiste(material.getTitulo())) {
                    request.getSession().setAttribute("existe", "¡El material ya existe!");
                    request.setAttribute("material", material);
                    acceso = agregar;
                    RequestDispatcher vista = request.getRequestDispatcher(acceso);
                    vista.forward(request, response);                    
                } else {
                    if (materialDao.insertarLibros(material, 1) > 0) {
                        request.getSession().setAttribute("exito", "Usuario agregado exitosamente!");
                        response.sendRedirect(request.getContextPath() + "/UsuarioControlador?accion=listar");
                        //acceso = listar;
                    } else {
                        request.getSession().setAttribute("error", "El usuario no ha podido ser agregado!");
                        response.sendRedirect(request.getContextPath() + "/UsuarioControlador?accion=listar");
                        //acceso = listar;
                    }
                }
            } catch (SQLException ex) {
               Logger.getLogger(MaterialControlador.class.getName()).log(Level.SEVERE, null, ex);
           }
        }else if(action.equals("editar")){
            acceso = editar;
            
            String id = request.getParameter("id");
            material = materialDao.ver(id);
            
            if(material != null){
                request.setAttribute("material", material);
                //request.getRequestDispatcher("/usuarios/editar.jsp").forward(request, response);
                RequestDispatcher vista = request.getRequestDispatcher(acceso);
                vista.forward(request, response);                     
            }else{
                response.sendRedirect(request.getContextPath() + "/error404.jsp");
            }
        }else if(action.equals("Actualizar")){
            try {
                listaErrores.clear();

                material.setTitulo(request.getParameter("titulo"));
                material.setTipoMaterial(request.getParameter("tipo_material"));
                material.setAutor(request.getParameter("Autor"));
                material.setNumeroDePaginas(request.getParameter("numero_de_paginas"));
                material.setEditorial(request.getParameter("editoriales"));
                material.setIsbn(request.getParameter("isbn"));
                material.setPeriodicidad(request.getParameter("periodicidad"));
                material.setFechaPublicacion(request.getParameter("fecha"));
                material.setArtista(request.getParameter("artistas"));
                material.setGenero(request.getParameter("generos"));
                material.setDuracion(request.getParameter("duracion"));
                material.setNumeroDeCanciones(request.getParameter("numero_de_canciones"));
                material.setDirector(request.getParameter("directores"));
                material.setUbicacion(request.getParameter("ubicacion"));
                material.setNombre_autor_CV(request.getParameter("nombre_autor_cv"));
                material.setUnidadesDisponibles(Integer.parseInt(request.getParameter("unidades_disponibles")));

                                if (Validaciones.isEmpty(material.getTitulo())) {
                    listaErrores.add("Campo Titulo obligatorio");
                }            
                if (Validaciones.isEmpty(material.getTipoMaterial())) {
                    listaErrores.add("Campo Tipo Material es obligatorio");
                }
                if (Validaciones.isEmpty(material.getAutor())) {
                    listaErrores.add("Campo Autor es obligatorio");
                }
                if (Validaciones.isEmpty(material.getNumeroDePaginas())) {
                    listaErrores.add("Campo numero de páginas es obligatorio");
                }
                if (Validaciones.isEmpty(material.getEditorial())) {
                    listaErrores.add("Campo Editorial es obligatorio");
                }
                if (Validaciones.isEmpty(material.getIsbn())) {
                    listaErrores.add("Campo ISBN es obligatorio");
                }
                if (Validaciones.isEmpty(material.getPeriodicidad())) {
                    listaErrores.add("Campo Periodicidad es obligatorio");
                }
                if (Validaciones.isEmpty(material.getFechaPublicacion().toString())) {
                    listaErrores.add("Campo Fecha Publicación es obligatorio");
                }
                if (Validaciones.isEmpty(material.getArtista())) {
                    listaErrores.add("Campo Artista es obligatorio");
                }
                if (Validaciones.isEmpty(material.getGenero())) {
                    listaErrores.add("Campo Genero es obligatorio");
                }
                if (Validaciones.isEmpty(material.getDuracion())) {
                    listaErrores.add("Campo Duración es obligatorio");
                }
                if (Validaciones.isEmpty(material.getNumeroDeCanciones())) {
                    listaErrores.add("Campo Numero de Canciones es obligatorio");
                }
                if (Validaciones.isEmpty(material.getDirector())) {
                    listaErrores.add("Campo Director es obligatorio");
                }
                if (Validaciones.isEmpty(material.getUbicacion())) {
                    listaErrores.add("Campo Ubicación es obligatorio");
                }
                if (Validaciones.isEmpty(material.getNombre_autor_CV())) {
                    listaErrores.add("Campo Autor de CV es obligatorio");
                }
                if (Validaciones.isEmpty(material.getUnidadesDisponibles().toString())) {
                    listaErrores.add("Campo Unidades Disponibles es obligatorio");
                }            

                if (listaErrores.size() > 0) {
                    request.setAttribute("listaErrores", listaErrores);
                    request.setAttribute("material", material);
                    //request.getRequestDispatcher("UsuarioControlador?accion=agregar").forward(request, response);
                    acceso = agregar;
                    RequestDispatcher vista = request.getRequestDispatcher(acceso);
                    vista.forward(request, response);                    
                } else {
                    if (materialDao.actualizar(material) > 0) {
                        request.getSession().setAttribute("exito", "Material actualizado exitosamente!");
                        response.sendRedirect(request.getContextPath() + "/MaterialControlador?accion=listar");
                        //acceso = listar;
                    } else {
                        request.getSession().setAttribute("error", "El material no ha podido ser actualizado!");
                        response.sendRedirect(request.getContextPath() + "/MaterialControlador?accion=listar");
                        //acceso = listar;
                    }
                }
            } catch (SQLException ex) {
               Logger.getLogger(MaterialControlador.class.getName()).log(Level.SEVERE, null, ex);
           }
        }else if(action.equals("eliminar")){
            String id = request.getParameter("id");

            if (materialDao.eliminar(id) > 0) {
                request.getSession().setAttribute("exito", "Material eliminado exitosamente!");
                response.sendRedirect(request.getContextPath() + "/MaterialControlador?accion=listar");
                //acceso = listar;
            } else {
                request.getSession().setAttribute("error", "El material no ha podido ser eliminado!");
                response.sendRedirect(request.getContextPath() + "/MaterialControlador?accion=listar");
                //acceso = listar;
            }                
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Controlador de materiales";
    }// </editor-fold>

}
