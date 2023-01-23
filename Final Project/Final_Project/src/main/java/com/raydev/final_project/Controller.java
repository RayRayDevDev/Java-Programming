package com.raydev.final_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.ArrayList;

public class Controller<QuizQuestions> {
    private Score score;
    private ArrayList<QuizQuestions> menu;
    private Comparisons Question;
    @FXML
    private Button nameButton, answerOneButton, answerTwoButton, answerThreeButton, answerFourButton, generateReportButton;
    @FXML
    private TextField usersName, nameEntry, totalPoints, totalTimeRemaining, questionOne, questionTwo;
    private int counter = 0;

    @FXML
    protected void setName(ActionEvent actionEvent) {

        try {
            if (usersName.getText() != null) {
                score = new Score(usersName.getText());
                nameButton.setVisible(false);
            } else usersName.setPromptText("You did not enter your name--Please try again!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        nextQuestion();

    }

    private void nextQuestion() {
        while (checkQuestions()) {
            Question = (Comparisons) menu.get(counter);
        }
    }
    public Boolean checkQuestions() {
        runningTotalOfScores.setText("");
        runningTotalOfScores.appendText(score.toString());
        return counter <= 9;
    }
    @FXML
    public void answerSelected(ActionEvent actionEvent) {
        Button selected = (Button) actionEvent.getSource();
        boolean isCorrect = false;
        if(isCorrect) {
            Question.compare(selected.getUserData());
            score.increaseScore(totalPoints.toString());
        }
        counter++;
        nextQuestion();
    }
    @FXML
    TextArea runningTotalOfScores;
    @FXML
    public void generateReport() {
        ParseScores handler = new ParseScores();
        ArrayList<Score> scoresReport = handler.winners();
        for (Score score : scoresReport) {
            runningTotalOfScores.appendText(score.toString());
        }
    }
}