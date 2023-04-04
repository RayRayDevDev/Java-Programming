<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add to To-Do List</title>
</head>
<body>
<h1>Add to To-Do List</h1>
<form action="toDoList" method="POST">
    <label for="note">Note:</label>
    <input type="text" id="note" name="note">
    <button type="submit" name="button" value="add">Add</button>
</form>
</body>
</html>
