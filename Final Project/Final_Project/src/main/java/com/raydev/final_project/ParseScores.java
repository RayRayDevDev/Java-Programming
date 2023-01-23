package com.raydev.final_project;

import java.sql.*;
import java.util.ArrayList;

public class ParseScores {
    private static final String DB_URL = "jdbc:mysql://localhost/TriviaGame";
    private static final String USER = "root";
    private static final String PASS = "root";
    Connection connection = null;
    Statement statement = null;

    public void insertNewScore(Score newScore) {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            String MySQL = "INSERT INTO GameResults (Name, Score, Time)" +
                    "VALUES ('" + newScore.getName() + "'," + newScore.getScore() +
                    "'," + newScore.getTime() + "')";
            statement.executeUpdate(MySQL);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public ArrayList<Score> winners() {
        ArrayList<Score> scores = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM GameResults;");
            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                int score = resultSet.getInt("Score");
                String time =  resultSet.getString("Time");
                scores.add(new Score(name, score, time));
                statement.close();
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return scores;
    }
}
