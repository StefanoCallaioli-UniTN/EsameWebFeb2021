package it.unitn.disi.stefanocallaioli;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author stefa
 */
public class verificaServlet extends HttpServlet {

    Connection conn = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String[] values = request.getQueryString().split("&");
        int id = Integer.parseInt(values[0].split("=")[1]);
        int riga = Integer.parseInt(values[1].split("=")[1]);
        int colonna = Integer.parseInt(values[2].split("=")[1]);
        Integer risposta = Integer.parseInt(values[3].split("=")[1]);
        
        System.out.println(id);
        System.out.println(riga);
        System.out.println(colonna);
        System.out.println(risposta);


        SudokuBean sudoku = new SudokuBean();
        sudoku.populate(request, id);
        
        int coordinata = (((riga)-1)*9+colonna)-1;
        Integer soluzione = sudoku.getSolution().get(coordinata);
        
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        System.out.println(soluzione);
        System.out.println(Boolean.toString(soluzione.equals(risposta)));
        
        out.write(Boolean.toString(soluzione.equals(risposta)));
        
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
