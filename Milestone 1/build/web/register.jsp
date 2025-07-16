<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <h2>Student Registration</h2>
    <form action="RegisterServlet" method="post">
        <label>Student Number:</label><br/>
        <input type="text" name="student_number" required><br/><br/>
        
        <label>Name:</label><br/>
        <input type="text" name="name" required><br/><br/>
        
        <label>Surname:</label><br/>
        <input type="text" name="surname" required><br/><br/>
        
        <label>Email:</label><br/>
        <input type="email" name="email" required><br/><br/>
        
        <label>Phone:</label><br/>
        <input type="text" name="phone" required><br/><br/>
        
        <label>Password:</label><br/>
        <input type="password" name="password" required><br/><br/>
        
        <input type="submit" value="Register">
    </form>
    
    <p><a href="index.jsp">Back to Home</a></p>
</body>
</html>

