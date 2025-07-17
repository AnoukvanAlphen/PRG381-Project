package conf;



import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.mindrot.jbcrypt.BCrypt;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
         Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
            e.printStackTrace(); // or log the error
            }
        
        try (Connection conn = utils.Database_connection.getconnection()) {

            String sql = "SELECT name, password FROM users WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHashedPassword = rs.getString("password");
                String name = rs.getString("name");

                if (BCrypt.checkpw(password, storedHashedPassword)) {
                    // Password is valid
                    HttpSession session = request.getSession();
                    session.setAttribute("studentName", name);
                    response.sendRedirect("dashboard.jsp");
                } else {
                    // Wrong password
                    response.sendRedirect("login.jsp?error=invalid");
                }
            } else {
                // Email not found
                response.sendRedirect("login.jsp?error=invalid");
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Database error: " + e.getMessage());
        }
    }
}

