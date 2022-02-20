package it.unitn.disi.stefanocallaioli;

import java.io.IOException;
import java.util.ListIterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author stefanocallaioli
 */

public class CreateSudokuServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Integer id = Integer.parseInt(request.getQueryString().split("=")[1]);
        SudokuBean sudoku = new SudokuBean();
        sudoku.populate(request, id);
        sudoku.createFixedCellValue();
        
        System.out.println(sudoku.getFixedCellsCord());
        System.out.println(sudoku.getFixedCellsValue());
        
        request.setAttribute("fixedCellsCord",sudoku.getFixedCellsCord());
        request.setAttribute("fixedCellsValue",sudoku.getFixedCellsValue());
        
        RequestDispatcher rd=request.getRequestDispatcher("/dynamicTable.jsp");  
        rd.forward(request, response); 

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
