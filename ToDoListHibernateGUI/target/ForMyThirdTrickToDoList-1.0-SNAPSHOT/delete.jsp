<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dev.rayraydev.ToDoListHibernateConsole.ToDoList" %>
<%@ page import="java.util.List" %>
<div style="text-align: center;">
    <form action="toDoList" method="POST">
        <table>
            <thead>
            <tr>
                <th>Delete</th>
                <th>ID</th>
                <th>Note</th>
            </tr>
            </thead>
            <tbody>
            <% List<ToDoList> toDoListy = (List<ToDoList>) request.getAttribute("toDoList");
                if (toDoListy == null || toDoListy.isEmpty()) { %>
            <tr>
                <td colspan="3">No notes found.</td>
            </tr>
            <%  } else {
                for (ToDoList toDo : toDoListy) { %>
            <tr>
                <td>
                    <input type="checkbox" name="deleteIds" value="<%= toDo.getNoteId() %>">
                </td>
                <td><%= toDo.getNoteId() %></td>
                <td><%= toDo.getNote() %></td>
            </tr>
            <%      }
            } %>
            </tbody>
        </table>
        <button type="submit" name="button" value="deleteSelected">Delete Selected</button>
    </form>
</div>
