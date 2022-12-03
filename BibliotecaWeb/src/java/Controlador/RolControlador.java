/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Rol;
import ModeloDAO.RolDAO;
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
public class RolControlador extends HttpServlet {
    
    String listar = "roles/listar.jsp";
    String agregar = "roles/agregar.jsp";
    String editar = "roles/editar.jsp";
    
    Rol rol = new Rol();
    RolDAO dao = new RolDAO();
    
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
            out.println("<title>Servlet UsuarioControlador</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsuarioControlador at " + request.getContextPath() + "</h1>");
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

                rol.setRol(request.getParameter("txtRol"));
                rol.setNumero_prestamos(Integer.parseInt(request.getParameter("txtNumeroPrestamos")));
                rol.setDias_prestamo(Integer.parseInt(request.getParameter("txtDiasPrestamo")));

                if (Validaciones.isEmpty(rol.getRol())) {
                    listaErrores.add("Campo Rol obligatorio");
                }            
                if (Validaciones.isEmpty(Integer.toString(rol.getNumero_prestamos()))) {
                    listaErrores.add("Campo Numero de Prestamos es obligatorio");
                }
                if (Validaciones.isEmpty(Integer.toString(rol.getDias_prestamo()))) {
                    listaErrores.add("Campo Dias de Prestamo es obligatorio");
                }       

                if (listaErrores.size() > 0) {
                    request.setAttribute("listaErrores", listaErrores);
                    request.setAttribute("rol", rol);
                    //request.getRequestDispatcher("UsuarioControlador?accion=agregar").forward(request, response);
                    acceso = agregar;
                    RequestDispatcher vista = request.getRequestDispatcher(acceso);
                    vista.forward(request, response);                    
                } else if (dao.rolExiste(rol.getRol())) {
                    request.getSession().setAttribute("existe", "El usuario ya existe!");
                    request.setAttribute("rol", rol);
                    //request.getRequestDispatcher("UsuarioControlador?accion=agregar").forward(request, response);
                    acceso = agregar;
                    RequestDispatcher vista = request.getRequestDispatcher(acceso);
                    vista.forward(request, response);                    
                } else {
                    if (dao.agregar(rol) > 0) {
                        request.getSession().setAttribute("exito", "Rol agregado exitosamente!");
                        response.sendRedirect(request.getContextPath() + "/RolControlador?accion=listar");
                        //acceso = listar;
                    } else {
                        request.getSession().setAttribute("error", "El Rol no ha podido ser agregado!");
                        response.sendRedirect(request.getContextPath() + "/RolControlador?accion=listar");
                        //acceso = listar;
                    }
                }
            } catch (SQLException ex) {
               Logger.getLogger(RolControlador.class.getName()).log(Level.SEVERE, null, ex);
           }
        }else if(action.equals("editar")){
            acceso = editar;
            
            String id = request.getParameter("id");
            rol = dao.ver(id);
            
            if(rol != null){
                request.setAttribute("rol", rol);
                //request.getRequestDispatcher("/usuarios/editar.jsp").forward(request, response);
                RequestDispatcher vista = request.getRequestDispatcher(acceso);
                vista.forward(request, response);                     
            }else{
                response.sendRedirect(request.getContextPath() + "/error404.jsp");
            }
        }else if(action.equals("Actualizar")){
            try {
                listaErrores.clear();

                rol.setRol(request.getParameter("txtRol"));
                rol.setNumero_prestamos(Integer.parseInt(request.getParameter("txtNumeroPrestamos")));
                rol.setDias_prestamo(Integer.parseInt(request.getParameter("txtDiasPrestamo")));

                if (Validaciones.isEmpty(rol.getRol())) {
                    listaErrores.add("Campo Rol obligatorio");
                }            
                if (Validaciones.isEmpty(Integer.toString(rol.getNumero_prestamos()))) {
                    listaErrores.add("Campo Numero de Prestamos es obligatorio");
                }
                if (Validaciones.isEmpty(Integer.toString(rol.getDias_prestamo()))) {
                    listaErrores.add("Campo Dias de Prestamo es obligatorio");
                }            

                if (listaErrores.size() > 0) {
                    request.setAttribute("listaErrores", listaErrores);
                    request.setAttribute("rol", rol);
                    //request.getRequestDispatcher("UsuarioControlador?accion=agregar").forward(request, response);
                    acceso = agregar;
                    RequestDispatcher vista = request.getRequestDispatcher(acceso);
                    vista.forward(request, response);                    
                } else {
                    if (dao.actualizar(rol) > 0) {
                        request.getSession().setAttribute("exito", "Rol actualizado exitosamente!");
                        response.sendRedirect(request.getContextPath() + "/RolControlador?accion=listar");
                        //acceso = listar;
                    } else {
                        request.getSession().setAttribute("error", "El Rol no ha podido ser actualizado!");
                        response.sendRedirect(request.getContextPath() + "/RolControlador?accion=listar");
                        //acceso = listar;
                    }
                }
            } catch (SQLException ex) {
               Logger.getLogger(RolControlador.class.getName()).log(Level.SEVERE, null, ex);
           }
        }else if(action.equals("eliminar")){
            String id = request.getParameter("id");

            if (dao.eliminar(id) > 0) {
                request.getSession().setAttribute("exito", "Rol eliminado exitosamente!");
                response.sendRedirect(request.getContextPath() + "/RolControlador?accion=listar");
                //acceso = listar;
            } else {
                request.getSession().setAttribute("error", "El Rol no ha podido ser eliminado!");
                response.sendRedirect(request.getContextPath() + "/RolControlador?accion=listar");
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
        return "Short description";
    }// </editor-fold>

}
