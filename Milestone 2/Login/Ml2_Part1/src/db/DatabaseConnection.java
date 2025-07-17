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
            
            //createUsers();
            //createAppointmentsTable();
            //createCounselorTable();
            //createFeedbackTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void dropUsersTable() {
    try {
        String sql = "DROP TABLE users";
        connection.createStatement().execute(sql);
        System.out.println("Users table dropped.");
    } catch (SQLException e) {
        System.out.println("Drop failed: " + e.getMessage());
    }
}
   public void createUsers() {
    try {
        String sql = "CREATE TABLE users (" +
                     "userid INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
                     "name VARCHAR(50), " +
                     "surname VARCHAR(50), " +
                     "email VARCHAR(100), " +
                     "username VARCHAR(50), " +
                     "password VARCHAR(50), " +
                     "verifycode VARCHAR(10), " +   // <-- COMMA here!
                     "verified BOOLEAN)";            // <-- Final column
        connection.createStatement().execute(sql);
        System.out.println("Users table created.");
    } catch (SQLException e) {
        if (e.getSQLState().equals("X0Y32")) {
            System.out.println("Users table already exists.");
        } else {
            e.printStackTrace();
        }
    }
}

    public void connectToDatabase() throws SQLException {
        
        String url = "jdbc:derby:WellnessDB;create=true";
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
    public void createFeedbackTable() {
    try {
        String sql = "CREATE TABLE feedback (" +
                     "id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
                     "name VARCHAR(50), " +
                     "surname VARCHAR(50), " +
                     "email VARCHAR(100), " +
                     "rating VARCHAR(20), " +
                     "comments VARCHAR(500))";
        connection.createStatement().execute(sql);
        System.out.println("Feedback table created.");
    } catch (SQLException e) {
        if (e.getSQLState().equals("X0Y32")) {
            System.out.println("Feedback table already exists.");
        } else {
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
