package com.raydev.final_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ParseFile<QuizQuestions> {
    private static final String IN_URL = "jdbc:mysql://localhost/input.txt";

    public <QuizQuestions> ArrayList<QuizQuestions> parseFile() {
        ArrayList<QuizQuestions> gameFileInputs = null;
        try {
            File gameInputs = new File(IN_URL);
            Scanner scanFile = new Scanner(gameInputs);
            ArrayList<Object> gameFileInputsButTemporary = new ArrayList<>();
            gameFileInputs = new ArrayList<>();
            while (scanFile.hasNext()) {
                if (scanFile.hasNextDouble()) gameFileInputsButTemporary.add(scanFile.nextDouble());
                else if (scanFile.hasNextInt()) gameFileInputsButTemporary.add(scanFile.nextInt());
                else gameFileInputsButTemporary.add(scanFile.next());
            }
            gameFileInputs.add((QuizQuestions) gameFileInputsButTemporary);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return gameFileInputs;
    }
}
