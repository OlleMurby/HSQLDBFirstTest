/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package omub.hsqldbfirsttest;


import java.sql.*; 
import java.util.logging.Level;
import java.util.logging.Logger;

import omub.utils.RandomNames ;

/**
 *
 * @author ollem
 */
public class HSQLDBFirstTest {

    public static void main(String[] args) {
        System.out.println("HSQLDBFirstTest: Olle's first crawling steps into database connections from java. This one into HSQLDB");
                
        String db = "" ;
        String user = ""; 
        String password = "" ;
        
	
        db = "jdbc:hsqldb:file:target/firsttests" ;
        user = "dev_user";
        password = "Admin123!";
		
        //For target/mydb - file based
	db = "jdbc:hsqldb:file:target/mydb" ;
	user = "SA" ;
        password = "" ;
		
	//For target/TestDB02 - file based HSSQDB access (i.e. in memory...)
	db = "jdbc:hsqldb:file:target/TestDB02" ;
	user = "SA" ;
        password = "password" ;
        
       	//For target/TestDB02 - Server based mod (i.e. HSQLDB Server has to be running, see example in C:\HSQLDB\hsqldb-2.7.2\hsqldb\)
        // Note: To start the HSQLDB Server, start a command prompt and issue the command 
        // "java -cp "C:\Users\ollem\.m2\repository\org\hsqldb\hsqldb\2.7.2\hsqldb-2.7.2.jar" org.hsqldb.server.Server --database.0 file:target/TestDB02 --dbname.0 TestDB02"
	db = "jdbc:hsqldb:hsql://localhost/TestDB02" ;
	user = "SA" ;
        password = "password" ;

/*  */      
        
        Connection conn = null;
        Statement stmt = null ;
        ResultSet rs = null ;
        String insertQuery = "" ; 
        try {
            conn = DriverManager.getConnection(db, user, password);

            int nextID = 0 ; 
            String str_nextID = "" ; 
            
            nextID = getNextIDAsInt( conn ) ; 
            str_nextID = String.format("%03d", nextID) ;
            
            insertQuery = "INSERT INTO PLAYER VALUES (" + nextID + " ,'" + RandomNames.rnd_lname() +"','password" + str_nextID + "')";
            stmt = conn.createStatement();
            stmt.execute(insertQuery);
            
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HSQLDBFirstTest.class.getName()).log(Level.SEVERE, null, ex);
            // If you get here from a failed connection, maybe it is because your HSQLDB Server is not running.. see comment above
            // the statement "	db = "jdbc:hsqldb:hsql://localhost/TestDB02" ;"
        }
        
        System.out.println("HSQLDBFirstTest: Have now inserted a row into the PLAYER table, using this SQL command: '" + insertQuery + "'." ) ;
        System.out.println("HSQLDBFirstTest: Program exited normally.");
        
    } // End of method main
     
    private static int getNextIDAsInt( Connection conn) {
            
        int loc_nextID = 0 ;
        String loc_strNextID = "" ; 
        
        try {
            Statement loc_stmt= conn.createStatement() ;
            ResultSet loc_rs = loc_stmt.executeQuery("SELECT MAX(ID) AS MAX_ID FROM PLAYER;") ;

            while ( loc_rs.next() ) {
                loc_strNextID = loc_rs.getString("MAX_ID") ;
            }
            loc_nextID = Integer.parseInt(loc_strNextID) ;
            loc_nextID ++ ;
        
        } catch (Exception e ) {
            
        }

        return loc_nextID ;
    
    } //End of method getNextIDAsString

} // End of Class HSQLDBFirstTest
