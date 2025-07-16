/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bc.welness.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author bosma
 */
public class DBConnection {
    private static final String DB_URL="jdbc:derby:welnessDB:create=true";
    private static Connection conn;
    
    public static Connection getConnection(){
        if(conn==null){
            try{
                conn=DriverManager.getConnection(DB_URL);
                System.out.println("Connected to Derby database");
            }
            catch(SQLException ex){
                System.out.println("Connection faield"+ ex.getMessage());
            }
        }
         return conn;
    }
    
    
}
