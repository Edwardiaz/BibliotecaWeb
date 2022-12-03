/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import ModeloDAO.UsuarioDAO;
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
public class UsuarioControlador extends HttpServlet {
    
    String listar = "usuarios/listar.jsp";
    String agregar = "usuarios/agregar.jsp";
    String editar = "usuarios/editar.jsp";
    
    Usuario usr = new Usuario();
    UsuarioDAO dao = new UsuarioDAO();
    
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

                usr.setNombre(request.getParameter("txtNombre"));
                usr.setApellido(request.getParameter("txtApellido"));
                usr.setNickname(request.getParameter("txtNickname"));
                usr.setEmail(request.getParameter("txtEmail"));
                usr.setFecha_nacimiento(request.getParameter("txtFechaNacimiento"));
                usr.setPass(request.getParameter("txtPass"));
                usr.setMora(Float.parseFloat(request.getParameter( "txtMora")));
                usr.setRol(request.getParameter("txtRol"));

                if (Validaciones.isEmpty(usr.getNombre())) {
                    listaErrores.add("Campo Nombre obligatorio");
                }            
                if (Validaciones.isEmpty(usr.getApellido())) {
                    listaErrores.add("Campo Apellido es obligatorio");
                }
                if (Validaciones.isEmpty(usr.getNickname())) {
                    listaErrores.add("Campo Nickname es obligatorio");
                }
                if (Validaciones.isEmpty(usr.getEmail())) {
                    listaErrores.add("Campo Email es obligatorio");
                }
                if (Validaciones.isEmpty(usr.getFecha_nacimiento())) {
                    listaErrores.add("Campo Fecha de Nacimiento es obligatorio");
                }
                if (Validaciones.isEmpty(usr.getPass())) {
                    listaErrores.add("Campo Contraseña es obligatorio");
                }
                if (Validaciones.isEmpty(usr.getMora().toString())) {
                    listaErrores.add("Campo Mora es obligatorio");
                }
                if (Validaciones.isEmpty(usr.getRol())) {
                    listaErrores.add("Campo Rol es obligatorio");
                }            

                if (listaErrores.size() > 0) {
                    request.setAttribute("listaErrores", listaErrores);
                    request.setAttribute("usuario", usr);
                    //request.getRequestDispatcher("UsuarioControlador?accion=agregar").forward(request, response);
                    acceso = agregar;
                    RequestDispatcher vista = request.getRequestDispatcher(acceso);
                    vista.forward(request, response);                    
                } else if (dao.usrExiste(usr.getEmail())) {
                    request.getSession().setAttribute("existe", "El usuario ya existe!");
                    request.setAttribute("usuario", usr);
                    //request.getRequestDispatcher("UsuarioControlador?accion=agregar").forward(request, response);
                    acceso = agregar;
                    RequestDispatcher vista = request.getRequestDispatcher(acceso);
                    vista.forward(request, response);                    
                } else {
                    if (dao.agregar(usr) > 0) {
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
               Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
           }
        }else if(action.equals("editar")){
            acceso = editar;
            
            String id = request.getParameter("id");
            usr = dao.ver(id);
            
            if(usr != null){
                request.setAttribute("usuario", usr);
                //request.getRequestDispatcher("/usuarios/editar.jsp").forward(request, response);
                RequestDispatcher vista = request.getRequestDispatcher(acceso);
                vista.forward(request, response);                     
            }else{
                response.sendRedirect(request.getContextPath() + "/error404.jsp");
            }
        }else if(action.equals("Actualizar")){
            try {
                listaErrores.clear();

                usr.setNombre(request.getParameter("txtNombre"));
                usr.setApellido(request.getParameter("txtApellido"));
                usr.setNickname(request.getParameter("txtNickname"));
                usr.setEmail(request.getParameter("txtEmail"));
                usr.setFecha_nacimiento(request.getParameter("txtFechaNacimiento"));
                usr.setPass(request.getParameter("txtPass"));
                usr.setMora(Float.parseFloat(request.getParameter( "txtMora")));
                usr.setRol(request.getParameter("txtRol"));

                if (Validaciones.isEmpty(usr.getNombre())) {
                    listaErrores.add("Campo Nombre obligatorio");
                }            
                if (Validaciones.isEmpty(usr.getApellido())) {
                    listaErrores.add("Campo Apellido es obligatorio");
                }
                if (Validaciones.isEmpty(usr.getNickname())) {
                    listaErrores.add("Campo Nickname es obligatorio");
                }
                if (Validaciones.isEmpty(usr.getEmail())) {
                    listaErrores.add("Campo Email es obligatorio");
                }
                if (Validaciones.isEmpty(usr.getFecha_nacimiento())) {
                    listaErrores.add("Campo Fecha de Nacimiento es obligatorio");
                }
                if (Validaciones.isEmpty(usr.getPass())) {
                    listaErrores.add("Campo Contraseña es obligatorio");
                }
                if (Validaciones.isEmpty(usr.getMora().toString())) {
                    listaErrores.add("Campo Mora es obligatorio");
                }
                if (Validaciones.isEmpty(usr.getRol())) {
                    listaErrores.add("Campo Rol es obligatorio");
                }            

                if (listaErrores.size() > 0) {
                    request.setAttribute("listaErrores", listaErrores);
                    request.setAttribute("usuario", usr);
                    //request.getRequestDispatcher("UsuarioControlador?accion=agregar").forward(request, response);
                    acceso = agregar;
                    RequestDispatcher vista = request.getRequestDispatcher(acceso);
                    vista.forward(request, response);                    
                } else {
                    if (dao.actualizar(usr) > 0) {
                        request.getSession().setAttribute("exito", "Usuario actualizado exitosamente!");
                        response.sendRedirect(request.getContextPath() + "/UsuarioControlador?accion=listar");
                        //acceso = listar;
                    } else {
                        request.getSession().setAttribute("error", "El usuario no ha podido ser actualizado!");
                        response.sendRedirect(request.getContextPath() + "/UsuarioControlador?accion=listar");
                        //acceso = listar;
                    }
                }
            } catch (SQLException ex) {
               Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
           }
        }else if(action.equals("eliminar")){
            String id = request.getParameter("id");

            if (dao.eliminar(id) > 0) {
                request.getSession().setAttribute("exito", "Usuario eliminado exitosamente!");
                response.sendRedirect(request.getContextPath() + "/UsuarioControlador?accion=listar");
                //acceso = listar;
            } else {
                request.getSession().setAttribute("error", "El usuario no ha podido ser eliminado!");
                response.sendRedirect(request.getContextPath() + "/UsuarioControlador?accion=listar");
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
