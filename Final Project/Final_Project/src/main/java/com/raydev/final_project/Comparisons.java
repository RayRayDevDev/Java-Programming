package com.raydev.final_project;

import java.util.ArrayList;

public class Comparisons {
    ArrayList<Object> quizList;
    ArrayList<String> quizNames = new ArrayList<>();
    public Comparisons(ArrayList<Object> object) {
        try {
            quizList = new ArrayList<>(object);
            for(Object O : object) {
                quizNames.add(String.valueOf(O));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean compare(Object userData) {
        String stringOne, stringTwo;
        Integer intOne, intTwo;
        Double douOne, douTwo;

        if(quizList.get(0) instanceof String) {
            stringOne = (String) quizList.get(0);
        } else if (quizList.get(0) instanceof Integer) {
            intOne = (Integer) quizList.get(0);
        } else if (quizList.get(0) instanceof Double) {
            douOne = (Double) quizList.get(0);
        }

        if(quizList.get(1) instanceof String) {
            stringTwo = (String) quizList.get(1);
        } else if (quizList.get(1) instanceof Integer) {
            intTwo = (Integer) quizList.get(1);
        } else if (quizList.get(1) instanceof Double) {
            douTwo = (Double) quizList.get(1);
        }
        return false;
    }
}
