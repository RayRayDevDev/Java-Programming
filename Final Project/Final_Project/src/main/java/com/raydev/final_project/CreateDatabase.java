package com.raydev.final_project;
import java.sql.*;

class CreateDatabase {
    private static final String DB_URL = "jdbc:mysql://localhost/";
    private static final String DB_NAME = "TriviaGame";

    private static final String USER = "root";
    private static final String PASS = "root";


public static void createNewDatabase() throws SQLException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();
            String mySQL = "CREATE DATABASE IF NOT EXISTS TriviaGame";
            statement.executeUpdate(mySQL);
            System.out.println("Database successfully created!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        createNewTable();
}

    private static void createNewTable() throws SQLException {
    Connection connection = DriverManager.getConnection(DB_URL + DB_NAME, USER, PASS);
    Statement statement = connection.createStatement();

    try {
        String mySQL = "CREATE TABLE IF NOT EXISTS GameResults " +
                "(id INTEGER NOT NULL, " +
                "Name VARCHAR(255) NOT NULL, " +
                "Score INTEGER NOT NULL, " +
                "Time VARCHAR(255), " +
                "PRIMARY KEY (id))";
        statement.executeUpdate(mySQL);
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    } finally {
        if (statement != null) {
            statement.close();
            connection.close();
        }
    }
    }

}