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
            createAppointmentsTable();
            createCounselorTable();
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
    public void createAppointmentsTable() {
    try {
        String sql = "CREATE TABLE appointments (" +
                     "id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
                     "name VARCHAR(50), " +
                     "surname VARCHAR(50), " +
                     "counselor VARCHAR(50), " +
                     "date DATE, " +
                     "time TIME)";
        connection.createStatement().execute(sql);
        System.out.println("Appointments table created.");
    } catch (SQLException e) {
        // If table already exists
        if (e.getSQLState().equals("X0Y32")) {
            System.out.println("Appointments table already exists.");
        } else {
            e.printStackTrace();
        }
    }
}
    public void createCounselorTable(){
        try{
             String sql = "CREATE TABLE counselors (" +
                     "id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
                     "name VARCHAR(50), " +
                     "surname VARCHAR(50), " +
                     "specialization VARCHAR(60), " +
                     "availability VARCHAR(50))";
        connection.createStatement().execute(sql);
        System.out.println("Counselors table created.");
        }catch(SQLException e){
                if (e.getSQLState().equals("X0Y32")) {
            System.out.println("Counselors table already exists.");
        }else{
                  e.printStackTrace();
            
    }
        }
    }

    public Connection getConnection() {
         try {
        if (connection == null || connection.isClosed()) {
            connectToDatabase();  // Reconnect if needed
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
