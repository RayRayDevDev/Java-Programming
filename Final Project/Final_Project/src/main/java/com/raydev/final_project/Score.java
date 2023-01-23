package com.raydev.final_project;

public class Score {
    private String name;
    public String getName() {
        return name;
    }

    private int score = 0;
    public int getScore() {
        return score;
    }
    private String time = "0";

    public String getTime() {
        return time;
    }
    private int intTime = 0;

    public Score(String name) {
        this.name = name;
    }

    public Score(String name, int score, String time) {
        this.name = name;
        this.score = score;
        this.time = time;
    }

    public void increaseScore(String s) {
        score++;
    }
    public void parseTime(int time) {
        intTime += time;
    }

    @Override
    public String toString(){
        return "\nName: " + name +
                "\nScore: " + score +
                "\nTime: " + time;
    }
}
