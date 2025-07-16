package controller.service;

import db.DatabaseConnection;
import model.ModelLogin;
import model.ModelUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Random;

public class ServiceUser {

    private final Connection con;

    public ServiceUser() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public ModelUser login(ModelLogin login) throws SQLException {
        ModelUser data = null;
        PreparedStatement p = con.prepareStatement(
            "SELECT userid, username, email FROM users " +
            "WHERE email = ? AND password = ? AND verified = true " +
            "FETCH FIRST 1 ROWS ONLY"
        );
        p.setString(1, login.getEmail());
        p.setString(2, login.getPassword());
        ResultSet r = p.executeQuery();
        if (r.next()) {
            int userID = r.getInt("userid");
            String userName = r.getString("username");
            String email = r.getString("email");
            data = new ModelUser(userID, userName, email, "");
        }
        r.close();
        p.close();
        return data;
    }

    public void insertUser(ModelUser user) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "INSERT INTO users (username, email, password, verifycode, verified) VALUES (?, ?, ?, ?, ?)",
            PreparedStatement.RETURN_GENERATED_KEYS
        );
        String code = generateVerifyCode();
        p.setString(1, user.getUserName());
        p.setString(2, user.getEmail());
        p.setString(3, user.getPassword());
        p.setString(4, code);
        p.setBoolean(5, false);  // Default: not verified

        p.executeUpdate();
        ResultSet r = p.getGeneratedKeys();
        if (r.next()) {
            user.setUserID(r.getInt(1));
            user.setVerifyCode(code);
        }
        r.close();
        p.close();
    }

    private String generateVerifyCode() throws SQLException {
        DecimalFormat df = new DecimalFormat("000000");
        Random ran = new Random();
        String code;
        do {
            code = df.format(ran.nextInt(1000000));
        } while (checkDuplicateCode(code));
        return code;
    }

    private boolean checkDuplicateCode(String code) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "SELECT userid FROM users WHERE verifycode = ? FETCH FIRST 1 ROWS ONLY"
        );
        p.setString(1, code);
        ResultSet r = p.executeQuery();
        boolean exists = r.next();
        r.close();
        p.close();
        return exists;
    }

    public boolean checkDuplicateUser(String username) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "SELECT userid FROM users WHERE username = ? AND verified = true FETCH FIRST 1 ROWS ONLY"
        );
        p.setString(1, username);
        ResultSet r = p.executeQuery();
        boolean exists = r.next();
        r.close();
        p.close();
        return exists;
    }

    public boolean checkDuplicateEmail(String email) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "SELECT userid FROM users WHERE email = ? AND verified = true FETCH FIRST 1 ROWS ONLY"
        );
        p.setString(1, email);
        ResultSet r = p.executeQuery();
        boolean exists = r.next();
        r.close();
        p.close();
        return exists;
    }

    public void doneVerify(int userID) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "UPDATE users SET verifycode = '', verified = true WHERE userid = ?"
        );
        p.setInt(1, userID);
        p.executeUpdate();
        p.close();
    }

    public boolean verifyCodeWithUser(int userID, String code) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "SELECT userid FROM users WHERE userid = ? AND verifycode = ? FETCH FIRST 1 ROWS ONLY"
        );
        p.setInt(1, userID);
        p.setString(2, code);
        ResultSet r = p.executeQuery();
        boolean exists = r.next();
        r.close();
        p.close();
        return exists;
    }
}
