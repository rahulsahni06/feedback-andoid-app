package com.example.rahulsahni.feedback;

/**
 * Created by Rahul Sahni on 08-Mar-18.
 */

public class Question {
    private String question;
    private String[] options;

    public Question(String question, String[] options) {
        this.question = question;
        this.options = options;
    }

    public String getQuestion() {
        return question;
    }

    public String getOption1() {
        return options[0];
    }

    public String getOption2() {
        return options[1];
    }

    public String getOption3() {
        return options[2];
    }

    public String getOption4() {
        return options[3];
    }
}
