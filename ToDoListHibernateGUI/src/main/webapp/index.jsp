<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>To-Do List</title>
</head>
<body>
<h1>To-Do List</h1>
<%
    String button = request.getParameter("button");
    if (button == null) {
%>
<form method="POST">
    <button type="submit" name="button" value="view">View To-Do List</button>
    <button type="submit" name="button" value="add">Add to To-Do List</button>
    <button type="submit" name="button" value="delete">Delete from To-Do List</button>
</form>
<%
    } else {
        if (button.equals("view")) {
            response.sendRedirect("view.jsp");
        } else if (button.equals("add")) {
            response.sendRedirect("add.jsp");
        } else if (button.equals("delete")) {
            response.sendRedirect("delete.jsp");
        } else {
            System.out.println("Unknown button value: " + button);
        }
    }
%>
</body>
</html>
