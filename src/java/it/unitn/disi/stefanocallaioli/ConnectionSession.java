package it.unitn.disi.stefanocallaioli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 *
 * @author stefanocallaioli
 */
public class ConnectionSession implements HttpSessionAttributeListener {
    
    static String dbURL = "jdbc:derby://localhost:1527/ExamDerbyDB";
    static String user = "WEBENGINE";
    static String password = "WEBENGINE";
    

    public ConnectionSession() {
    }
    
    static void createConnection(HttpServletRequest request){
        HttpSession session = request.getSession(true); //Crea sessione se non esiste
        if (session.getAttribute("connection") == null ){
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection conn = DriverManager.getConnection(dbURL, user, password);
                session.setAttribute("connection", conn); //E inserisci la connessione creata nella sessione
            } catch (ClassNotFoundException| SQLException ex) {
                ex.printStackTrace();
            }
        }  
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("Attributo aggiunto alla sessione");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        if(event.getName().equals("connection")){
            try {
                ((Connection)event.getValue()).close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
    }

}