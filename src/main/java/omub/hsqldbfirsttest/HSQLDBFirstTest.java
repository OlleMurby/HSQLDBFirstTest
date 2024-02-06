/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package omub.hsqldbfirsttest;


import java.sql.*; 
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ollem
 */
public class HSQLDBFirstTest {

    public static void main(String[] args) {
        System.out.println("Hello World!");
                
        String db = "jdbc:hsqldb:file:target/TestDB02";
        String user = ""; 
        String password = "" ;
        
        user = "dev_user";
        password = "Admin123!";
        user = "SA" ;
        password = "password" ;
        
        
        Connection conn = null;
        Statement stmt = null ;
        try {
            conn = DriverManager.getConnection(db, user, password);

            String insertQuery = "INSERT INTO PLAYER VALUES (1,'McKenzie','password')";
            stmt = conn.createStatement();
            stmt.execute(insertQuery);
            
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HSQLDBFirstTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
}
