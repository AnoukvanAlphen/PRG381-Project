<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
        <link rel="stylesheet" href="css/register.css">
    </head>
    <body>
        <div class="register-container">
            <h2>Sign-up</h2>

            <!-- Show validation message -->
            <c:if test="${not empty message}">
                <div class="error-message">${message}</div>
            </c:if>
               
            <form action="RegisterServlet" method="post" autocomplete="off">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>

                <label for="surname">Surname:</label>
                <input type="text" id="surname" name="surname" required>

                <label for="student_number">Student Number:</label>
                <input type="text" id="student_number" name="student_number" required>

                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>

                <label for="phone">Phone:</label>
                <input type="text" id="phone" name="phone" required>

                <label for="password">Password:</label>
                <input type="password" id="password" name="password" autocomplete="new-password" required>

                <input type="submit" value="Register">
            </form>

            <p><a href="login.jsp">Already have an account? Log in</a></p>
        </div>
    </body>
</html>
