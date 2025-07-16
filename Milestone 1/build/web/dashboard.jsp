<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
<%
    HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("studentName") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    String studentName = (String) session.getAttribute("studentName");
%>

    <h2>Welcome, <%= studentName %>!</h2>
    <form action="LogoutServlet" method="post">
        <input type="submit" value="Logout">
    </form>
</body>
</html>

