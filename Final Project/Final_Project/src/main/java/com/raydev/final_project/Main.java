package com.raydev.final_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

//Created with JDK-18.0.1.1
//Created by Cole Stanley (RäDev) for COP 3330C

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("gui.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Trivia Game!");
            stage.setScene(scene);
            stage.show();
        } catch (LoadException e) {
            System.out.println(e.getMessage());

        }
    }

    public static void main(String[] args) throws SQLException {
        CreateDatabase.createNewDatabase();
        launch();
    }
}