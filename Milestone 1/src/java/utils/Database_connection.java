/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 27763
 */
public class Database_connection {

    //private static final String URL = "jdbc:postgresql://db.vwuvfvxkwycbaejlzrwz.supabase.co:5432/postgres?sslmode=require";
    private static final String URL = "jdbc:postgresql://aws-0-ap-southeast-1.pooler.supabase.com:5432/postgres?user=postgres.vwuvfvxkwycbaejlzrwz&password=FSii1lxgkWsyDcX3";
    private static final String username = "postgres";
    private static final String password = "FSii1lxgkWsyDcX3";

    public static Connection getconnection() throws SQLException {
        return DriverManager.getConnection(URL, username, password);
    }

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found.");
        }
    }

}
