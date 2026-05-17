package org.example.model;

import lombok.Getter;

@Getter
public class Question {

    private final String text;

    private final String correctAnswer;

    public Question(String text, String correctAnswer) {
        this.text = text;
        this.correctAnswer = correctAnswer;
    }

    public boolean isCorrect(String answer) {
        if (answer == null)
            return false;
        return correctAnswer.equalsIgnoreCase(answer.trim());
    }

    @Override
    public String toString() {

        return text;
    }
}
