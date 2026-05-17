package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {

    private final String name;

    private int score = 0;

    private long totalTime = 0;

    public Player(String name) {

        this.name = name;
    }

    public void incrementScore() {

        score++;
    }

    public void addTime(long millis) {

        totalTime += millis;
    }

    @Override
    public String toString() {

        return name + " (score=" + score + ", time=" + totalTime + "ms)";
    }
}
