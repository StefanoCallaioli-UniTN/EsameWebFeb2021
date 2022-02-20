/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.stefanocallaioli;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author stefa
 */
public class SudokuBean implements Serializable{
    private int id;
    private ArrayList <Integer> fixedCellsCord = new ArrayList(81);
    private ArrayList <Integer> solution = new ArrayList(81);
    private ArrayList <Integer> fixedCellsValue = new ArrayList(81);

    public SudokuBean() {
    }

    public int getId() {
        return id;
    }

    public ArrayList<Integer> getFixedCellsCord() {
        return fixedCellsCord;
    }

    public ArrayList<Integer> getSolution() {
        return solution;
    }
    

    public ArrayList<Integer> getFixedCellsValue() {
        return fixedCellsValue;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setFixedCellsCord(ArrayList<Integer> fixedCellsCord) {
        this.fixedCellsCord = fixedCellsCord;
    }

    public void setSolution(ArrayList<Integer> solution) {
        this.solution = solution;
    }

    public void setFixedCellsValue(ArrayList<Integer> fixedCellsValue) {
        this.fixedCellsValue = fixedCellsValue;
    }


    
    
    public void populate(HttpServletRequest request, int id) {
        this.id=id;
        ConnectionSession.createConnection(request);
        HttpSession session = request.getSession();
        Connection con = (Connection)session.getAttribute("connection");

        
        try { // Create a prepared statement using connection object

            PreparedStatement preparedStatement = con.prepareStatement("SELECT FIXEDCELLS,SOLUTION FROM DATA WHERE ID=?");
            preparedStatement.setString(1, Integer.toString(id));
            
            ResultSet rs = preparedStatement.executeQuery();
            StringTokenizer st;
            while(rs.next()){     
                st = new StringTokenizer(rs.getString("FIXEDCELLS"));
                while (st.hasMoreTokens()) {
                    fixedCellsCord.add(Integer.parseInt(st.nextToken()));
                }
                st = new StringTokenizer(rs.getString("SOLUTION"));
                while (st.hasMoreTokens()) {
                    solution.add(Integer.parseInt(st.nextToken()));
                }
            }
            
        } catch (SQLException e) {
            // process sql exception
            System.err.print(e.toString());
        }  
    }   
    
    public void createFixedCellValue() {
        int indice, coordinata;
        for (ListIterator<Integer> iter = fixedCellsCord.listIterator(); iter.hasNext(); ) {
            indice = iter.next();
            coordinata = (((indice/10)-1)*9+indice%10)-1;
            fixedCellsValue.add(solution.get((coordinata)));  
        }
    }
    
    public String fixedCellsCordToJSON(){
        String JSON="[";
        for (ListIterator<Integer> iter = fixedCellsCord.listIterator(); iter.hasNext(); ) {  
            JSON += iter.next().toString();
        }
        JSON += "]";
        return JSON;
    }
}
