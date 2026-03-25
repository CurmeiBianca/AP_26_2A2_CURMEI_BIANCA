package org.example.advanced.repository;

import org.example.advanced.model.Resource;

import java.util.*;

public class Catalog {

    private final List<Resource> resources = new ArrayList<>();

    public void addResource(Resource res) {
        resources.add(res);
    }

    public List<Resource> getResources() {
        return resources;
    }

    // ----------------------------------------
    // Algoritmul Advanced: Set Cover Greedy
    // ----------------------------------------

    /**
     * Algoritmul este greedy:
     *   1. Cat timp exista concepte neacoperite:
     *     - alegem resursa care acopera cele mai multe concepte neacoperite
     *     - o adaugam in solutie
     *     - eliminam conceptele acoperite din multimea ramasa
     */
    public List<Resource> coverAllConcepts(Set<String> concepts) {

        Set<String> remaining = new HashSet<>(concepts); // concepte neacoperite
        List<Resource> solution = new ArrayList<>();

        while (!remaining.isEmpty()) {

            Resource best = null;
            int maxCovered = 0;

            // Cautam resursa care acopera cele mai multe concepte neacoperite
            for (Resource res : resources) {
                int covered = 0;

                for (String keywords : res.getKeywords()) {
                    if (remaining.contains(keywords)) {
                        covered++;
                    }
                }

                if (covered > maxCovered) {
                    maxCovered = covered;
                    best = res;
                }
            }

            // Daca nu mai putem acoperi nimic -> ne oprim
            if (best == null)
                break;

            solution.add(best);
            remaining.removeAll(best.getKeywords());
        }

        return solution;
    }

    // ----------------------------------------
    // Generator de resurse random pentru testarea performantei
    // ----------------------------------------

    /**
     * Genereaza n resurse random
     * Fiecare resursa primeste un subset aleatoriu din lista de concepte
     */
    public void generateRandomResources(int n, List<String> concepts) {
        Random rand = new Random();

        for (int i = 0; i < n; i++) {

            List<String> keys = new ArrayList<>();
            for (String conc : concepts) {
                if (rand.nextBoolean()) { // 50% sansa sa includem conceptul
                    keys.add(conc);
                }
            }

            addResource(new Resource("R" + i, keys));
        }
    }
}
