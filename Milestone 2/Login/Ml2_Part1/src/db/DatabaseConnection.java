package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private DatabaseConnection() {
        try {
            connectToDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void connectToDatabase() throws SQLException {
        String url = "jdbc:derby://localhost:1527/WellnessDB;create=true";
        String username = "app";
        String password = "app";
        connection = DriverManager.getConnection(url, username, password);
        System.out.println("Connected to JavaDB successfully.");
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
