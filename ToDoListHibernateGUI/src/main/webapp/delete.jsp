<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dev.rayraydev.ToDoListHibernateConsole.ToDoList" %>
<%@ page import="java.util.List" %>
<div style="text-align: center;">
    <form action="toDoList" method="POST" name="toDoListForm">
        <table style="width: 50%; margin: auto; border-collapse: collapse;">
            <thead>
            <tr>
                <th style="border: 1px solid black; padding: 5px;">Delete</th>
                <th style="border: 1px solid black; padding: 5px;">ID</th>
                <th style="border: 1px solid black; padding: 5px;">Note</th>
            </tr>
            </thead>
            <tbody>
            <% List<ToDoList> toDoListy = (List<ToDoList>) request.getAttribute("toDoList");
                if (toDoListy == null || toDoListy.isEmpty()) { %>
            <tr>
                <td colspan="3" style="border: 1px solid black; padding: 5px;">No notes found.</td>
            </tr>
            <%  } else {
                for (ToDoList toDo : toDoListy) { %>
            <tr>
                <td style="border: 1px solid black; padding: 5px;">
                    <input type="checkbox" name="deleteIds" value="<%= toDo.getNoteId() %>">
                </td>
                <td style="border: 1px solid black; padding: 5px;"><%= toDo.getNoteId() %></td>
                <td style="border: 1px solid black; padding: 5px;"><%= toDo.getNote() %></td>
            </tr>
            <%      }
            } %>
            </tbody>
        </table>
        <button type="submit" name="button" value="deleteSelected">Delete Selected</button>
    </form>
</div>
