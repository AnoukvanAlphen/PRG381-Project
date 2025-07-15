/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template

 */
package db;
import java.sql.*;

/**
 *
 * @author nicho
 */
public class DBConnection {
     private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
     private static final String JDBC_URL = "jdbc:derby:wellnessDB;create=true";
     Connection con;
     
     public DBConnection(){}
     
     public void connect()throws ClassNotFoundException{
         try{
         Class.forName(DRIVER);
         this.con = DriverManager.getConnection(JDBC_URL);
         if (this.con != null) {
             System.out.println("Connected");
             
         }}catch(SQLException ex){
                 ex.printStackTrace();
                 }
     }
}
