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
public class LoginControlador extends HttpServlet {
    String login = "login.jsp";
    String index = "index.jsp";
    
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
            out.println("<title>Servlet LoginControlador</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginControlador at " + request.getContextPath() + "</h1>");
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
        if(action.equalsIgnoreCase("login")){
            response.sendRedirect(request.getContextPath() + "/login.jsp");           
        }else if(action.equals("Ingresar")){
            try {
                listaErrores.clear();

                usr.setNickname(request.getParameter("txtNickname"));
                usr.setPass(request.getParameter("txtPass"));

                if (Validaciones.isEmpty(usr.getNickname())) {
                    listaErrores.add("Campo Nickname es obligatorio");
                }
                if (Validaciones.isEmpty(usr.getPass())) {
                    listaErrores.add("Campo Contraseña es obligatorio");
                }

                if (listaErrores.size() > 0) {
                    request.setAttribute("listaErrores", listaErrores);
                    request.setAttribute("usuario", usr);
                    acceso = login;
                    RequestDispatcher vista = request.getRequestDispatcher(acceso);
                    vista.forward(request, response);                    
                }else {
                    String nickname = request.getParameter("txtNickname");
                    String pass = request.getParameter("txtPass");

                    usr = dao.login(nickname,pass);
                    
                    if (usr.id != 0) {
                        request.getSession().setAttribute("idUsuario",usr.id);
                        request.getSession().setAttribute("nombreUsuario",usr.nombre + " " +usr.apellido);
                        request.getSession().setAttribute("nickname",usr.nickname);
                        request.getSession().setAttribute("rolUsuario",usr.codigo_rol);
                        
                        request.getSession().setAttribute("exito", "Bienvenido "+ usr.nombre +"!");
                        response.sendRedirect(request.getContextPath() + "/index.jsp"); 
                    } else {
                        request.getSession().setAttribute("error", "Datos Incorrectos!");
                        acceso = login;
                        RequestDispatcher vista = request.getRequestDispatcher(acceso);
                        vista.forward(request, response);  
                    }
                }
            } catch (SQLException ex) {
               Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
           }
        }else if(action.equals("logout")){
            request.getSession().setAttribute("idUsuario",null);
            request.getSession().setAttribute("nombreUsuario",null);
            request.getSession().setAttribute("nickname",null);
            request.getSession().setAttribute("rolUsuario",null);

            response.sendRedirect(request.getContextPath() + "/index.jsp"); 
        }else if(action.equals("Restablecer")){
            try {
                listaErrores.clear();

                usr.setNickname(request.getParameter("txtNickname2"));
                usr.setEmail(request.getParameter("txtEmail2"));
                usr.setFecha_nacimiento(request.getParameter("txtFechaNacimiento2"));
                usr.setPass(request.getParameter("txtNewPass"));

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

                if (listaErrores.size() > 0) {
                    request.setAttribute("listaErrores", listaErrores);
                    request.setAttribute("usuario", usr);
                    //request.getRequestDispatcher("UsuarioControlador?accion=agregar").forward(request, response);
                    acceso = login;
                    RequestDispatcher vista = request.getRequestDispatcher(acceso);
                    vista.forward(request, response);                    
                } else {
                    if (dao.cambiarPass(usr) > 0) {
                        request.getSession().setAttribute("exito", "Clave de usuario restablecida exitosamente!");
                        response.sendRedirect(request.getContextPath() + "/index.jsp");
                    } else {
                        request.getSession().setAttribute("error", "La clave de usuario no ha podido ser restablecida!");
                        response.sendRedirect(request.getContextPath() + "/index.jsp");
                    }
                }
            } catch (SQLException ex) {
               Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
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
