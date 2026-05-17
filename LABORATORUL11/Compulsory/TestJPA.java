package org.example.compulsory;

import org.example.compulsory.model.Player;
import org.example.compulsory.repository.PlayerRepository;

public class TestJPA {
    public static void main(String[] args) {

        PlayerRepository repo = new PlayerRepository();

        Player player = new Player("Bianca");
        player.setScore(10);
        player.setTotalTime(5000);

        repo.save(player);

        Player found = repo.findById(player.getId());
        System.out.println("Found player: " + found.getName() +
                " | score = " + found.getScore() +
                " | time = " + found.getTotalTime());
    }
}
