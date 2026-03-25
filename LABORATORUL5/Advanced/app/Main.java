package org.example.advanced.app;

import org.example.advanced.model.Resource;
import org.example.advanced.repository.Catalog;

import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Catalog catalog = new Catalog();

        Set<String> stringSet = Set.of(
                "Graph theory",
                "Neural Networks",
                "Algorithm design techniques",
                "Object-oriented programming"
        );

        catalog.addResource(new Resource("1", List.of("Object-oriented programming")));
        catalog.addResource(new Resource("2", List.of("Neural Networks", "Algorithm design techniques")));
        catalog.addResource(new Resource("3", List.of("Graph theory")));

        System.out.println("=== SET COVER ===");
        List<Resource> cover = catalog.coverAllConcepts(stringSet);

        for (Resource res : cover) {
            System.out.println("Resource " + res.getId() + " acopera: " + res.getKeywords());
        }

        Catalog randomCatalog = new Catalog();
        randomCatalog.generateRandomResources(1000, List.copyOf(stringSet));

        long start = System.currentTimeMillis();
        randomCatalog.coverAllConcepts(stringSet);
        long end = System.currentTimeMillis();

        System.out.println("\nTimp executie instante random: " + (end - start) + " ms");
    }
}
