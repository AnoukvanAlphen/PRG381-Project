<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<link rel="stylesheet" href="css/login.css">

<body>
    <div class="login-container">
        <h2>Student Login</h2>

        <% String error = request.getParameter("error");
           if ("invalid".equals(error)) { %>
            <p class="error-message">Invalid email or password. Please try again.</p>
        <% } %>

        <form action="LoginServlet" method="post">
            <label>Email:</label>
            <input type="email" name="email" required>

            <label>Password:</label>
            <input type="password" name="password" required>

            <button type="submit">Log in</button>

        </form>

        <p><a href="index.jsp">Back to Home</a></p>
    </div>
</body>

</html>
