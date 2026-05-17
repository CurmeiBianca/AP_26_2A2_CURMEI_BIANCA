package org.example.compulsory.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.example.compulsory.model.Player;

public class PlayerRepository {

    private static final EntityManagerFactory entitymf =
            Persistence.createEntityManagerFactory("quizPU");

    public void save(Player player) {
        EntityManager entityManager = entitymf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(player);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Player findById(Long id) {
        EntityManager entityManager = entitymf.createEntityManager();
        Player player = entityManager.find(Player.class, id);
        entityManager.close();
        return player;
    }
}
