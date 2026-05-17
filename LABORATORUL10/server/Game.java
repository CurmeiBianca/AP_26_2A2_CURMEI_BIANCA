package org.example.server;

import lombok.Getter;
import org.example.model.Player;
import org.example.model.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Getter
public class Game {

    private final List<Question> questions = new ArrayList<>();

    private final List<Player> players = Collections.synchronizedList(new ArrayList<>());

    public Game() {

        loadQuestions();
    }

    private void loadQuestions() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("questions.txt");

            if (inputStream == null) {
                System.err.println("Nu s-a gasit fisierul questions.txt in resources!");
                return;
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");

                    if (parts.length == 2) {
                        questions.add(new Question(parts[0].trim(), parts[1].trim()));
                    }
                }
            }

            System.out.println("Loaded " + questions.size() + " questions");

        } catch (IOException exception) {
            System.err.println("Eroare la incarcarea intrebarilor: " + exception.getMessage());
        }
    }

    public void addPlayer(Player player) {

        players.add(player);
    }

    public Question getQuestion(int index) {
        if (index < 0 || index >= questions.size())
            return null;
        return questions.get(index);
    }

    public int getQuestionCount() {

        return questions.size();
    }

    public Player getWinner() {
        if (players.isEmpty())
            return null;

        return players.stream()
                .sorted(
                        Comparator.comparingInt(Player::getScore).reversed()
                                .thenComparingLong(Player::getTotalTime)
                )
                .findFirst()
                .orElse(null);
    }
}
