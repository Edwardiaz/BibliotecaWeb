/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Materiales;
import Modelo.Prestamos;
import Modelo.Usuario;
import ModeloDAO.PrestamoDAO;
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
 * @author user
 */
public class PrestamoControlador extends HttpServlet {

    String listar = "prestamos/listar.jsp";
    //String devolver = "prestamos/devolver.jsp";
    String agregar = "prestamos/agregar.jsp";
    // String agregar = "usuarios/agregar.jsp";
    // editar = "usuarios/editar.jsp";

    Prestamos pres = new Prestamos();
    PrestamoDAO dao = new PrestamoDAO();

    Materiales mat = new Materiales();
    Usuario user = new Usuario();
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
            out.println("<title>Servlet PrestamoControlador</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PrestamoControlador at " + request.getContextPath() + "</h1>");
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

        if (action.equalsIgnoreCase("listar")) {
            acceso = listar;
            RequestDispatcher vista = request.getRequestDispatcher(acceso);
            vista.forward(request, response);
        } else if (action.equals("add")) {
            acceso = agregar;
            RequestDispatcher vista = request.getRequestDispatcher(acceso);
            vista.forward(request, response);
        } else if (action.equals("Agregar")) {
            try {
                listaErrores.clear();

                pres.setCodigoMaterial(request.getParameter("txtMaterial"));
                pres.setCodigoUsuario(Integer.parseInt(request.getParameter("txtUsuario")));

                if (Validaciones.isEmpty(pres.getCodigoMaterial())) {
                    listaErrores.add("Campo material obligatorio");
                }

                int nickname = Integer.parseInt(request.getParameter("txtUsuario"));
                String material = request.getParameter("txtMaterial");
                
                request.getSession().setAttribute("materialId", material);
                request.getSession().setAttribute("userId", nickname);
                        
                if (listaErrores.size() > 0) {
                    request.setAttribute("listaErrores", listaErrores);
                    request.setAttribute("prestamos", pres);
                    acceso = agregar;
                    RequestDispatcher vista = request.getRequestDispatcher(acceso);
                    vista.forward(request, response);
                } else if (dao.verificarPrestamo(nickname, material)) {
                    request.getSession().setAttribute("existe", "El préstamo ya existe!");
                    request.setAttribute("prestamos", pres);
                    acceso = agregar;
                    RequestDispatcher vista = request.getRequestDispatcher(acceso);
                    vista.forward(request, response);
                } else if (dao.verificarMora(nickname)) {
                    request.getSession().setAttribute("error", "No puede hacer prestamo, posee mora.");
                    request.setAttribute("prestamos", pres);
                    acceso = agregar;
                    RequestDispatcher vista = request.getRequestDispatcher(acceso);
                    vista.forward(request, response);
                } else if (!dao.materialDisponible(material)) {
                    request.getSession().setAttribute("error", "No hay suficiente material para prestar.");
                    acceso = agregar;
                    RequestDispatcher vista = request.getRequestDispatcher(acceso);
                    vista.forward(request, response);
                } else {
                    if (dao.agregarPrestamo(nickname, material) > 0) {
                        request.getSession().setAttribute("exito", "Préstamo agregado exitosamente!");
                        response.sendRedirect(request.getContextPath() + "/PrestamoControlador?accion=listar");
                        //acceso = listar;
                    } else {
                        request.getSession().setAttribute("error", "El prestamo no ha podido ser agregado!");
                        response.sendRedirect(request.getContextPath() + "/PrestamoControlador?accion=listar");
                        //acceso = listar;
                    }

                }

            } catch (SQLException ex) {

            }

        } else if (action.equals("devolver")) {
            try {
                String materialId = request.getParameter("materialId");
                int userId = Integer.parseInt(request.getParameter("userId"));
                
                if (dao.regresarMaterial(materialId, userId) > 1) {
                    request.getSession().setAttribute("error", "El préstamo no ha podido ser eliminado!");
                    response.sendRedirect(request.getContextPath() + "/PrestamoControlador?accion=listar");
                    
                    //acceso = listar;
                } else {
                    request.getSession().setAttribute("exito", "Préstamo eliminado exitosamente!");
                    response.sendRedirect(request.getContextPath() + "/PrestamoControlador?accion=listar");
                    //acceso = listar;
                }
            } catch (SQLException ex) {
                Logger.getLogger(PrestamoControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
