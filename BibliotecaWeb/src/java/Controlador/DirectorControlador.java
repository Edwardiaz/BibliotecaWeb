/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Director;
import ModeloDAO.DirectorDAO;
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
 * @author Eduardo Trujillo
 */
public class DirectorControlador extends HttpServlet {

    String listar = "directores/listar.jsp";
    String agregar = "directores/agregar.jsp";
    String editar = "directores/editar.jsp";
    
    Director director = new Director();
    DirectorDAO dao = new DirectorDAO();
    
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
            out.println("<title>Servlet DirectorControlador</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DirectorControlador at " + request.getContextPath() + "</h1>");
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

                director.setNombre_director(request.getParameter("txtNombre"));

                if (Validaciones.isEmpty(director.getNombre_director())) {
                    listaErrores.add("Campo Nombre obligatorio");
                }     

                if (listaErrores.size() > 0) {
                    request.setAttribute("listaErrores", listaErrores);
                    request.setAttribute("director", director);
                    acceso = agregar;
                    RequestDispatcher vista = request.getRequestDispatcher(acceso);
                    vista.forward(request, response);                    
                } else if (dao.directorExiste(director.getNombre_director())) {
                    request.getSession().setAttribute("existe", "El director ya existe!");
                    request.setAttribute("director", director);
                    acceso = agregar;
                    RequestDispatcher vista = request.getRequestDispatcher(acceso);
                    vista.forward(request, response);                    
                } else {
                    if (dao.agregar(director) > 0) {
                        request.getSession().setAttribute("exito", "Director agregado exitosamente!");
                        response.sendRedirect(request.getContextPath() + "/DirectorControlador?accion=listar");
                    } else {
                        request.getSession().setAttribute("error", "El director no ha podido ser agregado!");
                        response.sendRedirect(request.getContextPath() + "/DirectorControlador?accion=listar");
                    }
                }
            } catch (SQLException ex) {
               Logger.getLogger(DirectorControlador.class.getName()).log(Level.SEVERE, null, ex);
           }
        }else if(action.equals("editar")){
            acceso = editar;
            
            String id = request.getParameter("id");
            director = dao.ver(id);
            
            if(director != null){
                request.setAttribute("director", director);
                RequestDispatcher vista = request.getRequestDispatcher(acceso);
                vista.forward(request, response);                     
            }else{
                response.sendRedirect(request.getContextPath() + "/error404.jsp");
            }
        }else if(action.equals("Actualizar")){
            try {
                listaErrores.clear();

                director.setNombre_director(request.getParameter("txtNombre"));

                if (Validaciones.isEmpty(director.getNombre_director())) {
                    listaErrores.add("Campo Nombre obligatorio");
                }            

                if (listaErrores.size() > 0) {
                    request.setAttribute("listaErrores", listaErrores);
                    request.setAttribute("director", director);
                    acceso = agregar;
                    RequestDispatcher vista = request.getRequestDispatcher(acceso);
                    vista.forward(request, response);                    
                } else {
                    if (dao.actualizar(director) > 0) {
                        request.getSession().setAttribute("exito", "Director actualizado exitosamente!");
                        response.sendRedirect(request.getContextPath() + "/DirectorControlador?accion=listar");
                    } else {
                        request.getSession().setAttribute("error", "El Director no ha podido ser actualizado!");
                        response.sendRedirect(request.getContextPath() + "/DirectorControlador?accion=listar");
                        //acceso = listar;
                    }
                }
            } catch (SQLException ex) {
               Logger.getLogger(DirectorControlador.class.getName()).log(Level.SEVERE, null, ex);
           }
        }else if(action.equals("eliminar")){
            String id = request.getParameter("id");

            if (dao.eliminar(id) > 0) {
                request.getSession().setAttribute("exito", "Director eliminado exitosamente!");
                response.sendRedirect(request.getContextPath() + "/DirectorControlador?accion=listar");
            } else {
                request.getSession().setAttribute("error", "El Director no ha podido ser eliminado!");
                response.sendRedirect(request.getContextPath() + "/DirectorControlador?accion=listar");
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
        return "Short description";
    }// </editor-fold>

}
