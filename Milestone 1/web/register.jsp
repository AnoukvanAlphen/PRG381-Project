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

            <!-- Server-side validation message -->
            <c:if test="${not empty message}">
                <div class="error-message">${message}</div>
            </c:if>
            <c:if test="${not empty message}">
                <div class="success-message">${message}</div>
            </c:if>


            <form action="RegisterServlet" method="post" autocomplete="off" id="registerForm">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" value="${prevName}" required>
                <div id="name-error" class="error-text"></div>


                <label for="surname">Surname:</label>
                <input type="text" id="surname" name="surname" value="${prevSurname}" required>
                <div id="surname-error" class="error-text"></div>

                <label for="student_number">Student Number:</label>
                <input type="text" id="student_number" name="student_number" value="${prevStudentNumber}" required>
                <div id="student_number-error" class="error-text"></div>

                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${prevEmail}" required>
                <div id="email-error" class="error-text"></div>

                <label for="phone">Phone:</label>
                <input type="text" id="phone" name="phone" value="${prevPhone}" required>
                <div id="phone-error" class="error-text"></div>

                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
                <div id="password-error" class="error-text"></div>

                <input type="submit" value="Register">
            </form>

            <p><a href="login.jsp">Already have an account? Log in</a></p>
        </div>

        <script src="javascript/register.js"></script>

    </body>
</html>
