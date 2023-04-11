<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, dev.rayraydev.ToDoListHibernateConsole.ToDoList" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>To-Do List</title>
</head>
<body>
<h1>To-Do List</h1>
<table style="margin: 0 auto; text-align: center;">
    <thead>
    <tr>
        <th>ID</th>
        <th>Note</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<ToDoList> toDoList = (List<ToDoList>) request.getAttribute("toDoList");
        if (toDoList != null) {
            for (ToDoList toDo : toDoList) {
    %>
    <tr>
        <td><%= toDo.getNoteId() %></td>
        <td><%= toDo.getNote() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="2">No items found</td>
    </tr>
    <%
        }
    %>

    </tbody>
</table>
<a href="index.jsp">Home</a>
</body>
</html>
