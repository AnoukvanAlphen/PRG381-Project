/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package conf;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author 27763
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String studentNumber = request.getParameter("student_number");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        if (studentNumber == null || studentNumber.isEmpty()
                || name == null || name.isEmpty()
                || surname == null || surname.isEmpty()
                || email == null || email.isEmpty()
                || password == null || password.isEmpty()) {
            request.setAttribute("message", "Please fill in all required fields");
            request.setAttribute("prevStudentNumber", studentNumber);
            request.setAttribute("prevName", name);
            request.setAttribute("prevSurname", surname);
            request.setAttribute("prevEmail", email);
            request.getRequestDispatcher("register.jsp").forward(request, response);

            return;

        }

        // <editor-fold defaultstate="collapsed" desc="email validation">
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern email_pattern = Pattern.compile(emailRegex);
        Matcher email_matcher = email_pattern.matcher(email);

        if (email_matcher.matches() == false) {
            request.setAttribute("message", "please enter a valid email");
            request.setAttribute("prevEmail", email);
            request.getRequestDispatcher("register.jsp").forward(request, response);

            return;
        }

        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="password strength validation">
        String passRegexStrong = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
        String passRegexMedium = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}$";
        String passRegexWeak = "^(?=.*[A-Z])(?=.*[0-9]).{8,}$";

        String strengthLevel;
        if (Pattern.matches(passRegexStrong, password)) {
            strengthLevel = "strong";
        } else if (Pattern.matches(passRegexMedium, password)) {
            strengthLevel = "medium";
        } else if (Pattern.matches(passRegexWeak, password)) {
            strengthLevel = "weak";
        } else {
            strengthLevel = "invalid";
        }

        switch (strengthLevel) {
            case "strong":
                break;

            case "medium":
                request.setAttribute("message", "Password strength is medium. Consider adding symbols.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;

            case "weak":
                request.setAttribute("message", "Password is weak. Add lowercase letters and symbols.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;

            case "invalid":
                request.setAttribute("message", "Password does not meet minimum requirements.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
        }

        // </editor-fold>  
        // <editor-fold defaultstate="collapsed" desc="phone number validation">
        String phoneRegex = "^(0[6-8][0-9]{8})$";
        Pattern phone_pattern = Pattern.compile(phoneRegex);
        Matcher phone_matcher = phone_pattern.matcher(phone);
        if (phone_matcher.matches() == false) {
            request.setAttribute("message", "please enter a valid phone number");
            request.setAttribute("prevPhone", phone);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="post method + connect">
        try {

            try (Connection conn = utils.Database_connection.getconnection()) {

                String checkQuery = "SELECT 1 FROM users WHERE email = ? OR student_number = ?";
                try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                    checkStmt.setString(1, email);
                    checkStmt.setString(2, studentNumber);

                    try (ResultSet rs = checkStmt.executeQuery()) {
                        if (rs.next()) {
                            request.setAttribute("message", "Email or Student Number already exists.");
                            request.getRequestDispatcher("register.jsp").forward(request, response);
                            return;
                        }
                    }
                }

                String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

                String insertQuery = "INSERT INTO users (student_number, name, surname, email, phone, password) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                    insertStmt.setString(1, studentNumber);
                    insertStmt.setString(2, name);
                    insertStmt.setString(3, surname);
                    insertStmt.setString(4, email);
                    insertStmt.setString(5, phone);
                    insertStmt.setString(6, hashedPassword);

                    int rowsInserted = insertStmt.executeUpdate();

                    if (rowsInserted > 0) {
                        request.setAttribute("registrationSuccess", "Registration successful! You can now log in.");
                        request.getRequestDispatcher("register.jsp").forward(request, response);

                    } else {
                        request.setAttribute("message", "Registration failed. Please try again.");
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Something went wrong: " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

}

// </editor-fold>

