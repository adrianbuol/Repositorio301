/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Adrián Bueno Olmedo <adrian.bueno.alum@iescamp.es>
 */
@WebServlet(name = "MainController", urlPatterns = {"", "/public/*"})
public class MainController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getPathInfo().equals("/")) {
            servirVistas(request, response);
        } else {
            servirEstatico(request, response);
        }
    }

    private void servirVistas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // def
        String page = "WEB-INF/views/landing.jsp";

        // Redireccionado
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);

        // Redireccion
        dispatcher.forward(request, response);

    }

    private void servirEstatico(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        // Nombre del Recurso
        String recursoIni = request.getPathInfo();

        // Recurso adaptado
        String recursoFin = "/WEB-INF/static" + recursoIni;

        // Ruta Absoluta
        String ruta = request.getPathTranslated();

        // Adaptar ruta
        ruta = ruta.replace("\\", "/");

        // Recurso ini > recurso fin
        ruta = ruta.replace(recursoIni, recursoFin);

        // Nombre de Recurso > Fichero
        File fichero = new File(recursoIni);

        try {
            FileInputStream in = new FileInputStream(fichero);
            ServletOutputStream out = response.getOutputStream();

            // Buffer de Transferencia
            byte[] buffer = new byte[(int) fichero.length()];

            // Lectura
            in.read(buffer);

            // Escritura
            out.write(buffer);

        } catch (Exception e) {
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
        processRequest(request, response);
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
