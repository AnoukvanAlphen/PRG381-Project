<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Student Login</h2>
    
    <% String error = request.getParameter("error");
       if ("invalid".equals(error)) { %>
        <p style="color:red;">Invalid email or password. Please try again.</p>
    <% } %>
    
    <form action="LoginServlet" method="post">
        <label>Email:</label><br/>
        <input type="email" name="email" required><br/><br/>
        
        <label>Password:</label><br/>
        <input type="password" name="password" required><br/><br/>
        
        <input type="submit" value="Login">
    </form>
    
    <p><a href="index.jsp">Back to Home</a></p>
</body>
</html>
