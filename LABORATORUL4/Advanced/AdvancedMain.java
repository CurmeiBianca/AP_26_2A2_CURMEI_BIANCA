package org.example.advanced;

import org.example.compulsory.Intersection;
import org.example.homework.City;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class AdvancedMain {

    public static void main(String[] args) {

        City city = City.generateRandomCity(10, 15);

        GraphAlgorithms algorithms = new GraphAlgorithms();
        AlternativeMSTGenerator generator = new AlternativeMSTGenerator();

        // 1. MST cu Prim
        System.out.println("=== MST cu algoritmul Prim ===");
        Graph<Intersection, DefaultWeightedEdge> primMST =
                algorithms.computeMSTPrim(city.toGraph());

        System.out.println("Cost total Prim: " +
                primMST.edgeSet().stream().mapToDouble(primMST::getEdgeWeight).sum());

        // 2. Primele 3 MST-uri alternative
        System.out.println("\n=== Primele 3 MST-uri alternative ===");
        var mstList = generator.getKBestMSTs(city, 3);

        int index = 1;
        for (Graph<Intersection, DefaultWeightedEdge> mst : mstList) {
            System.out.println("\nMST #" + index);
            System.out.println("Cost total: " +
                    mst.edgeSet().stream().mapToDouble(mst::getEdgeWeight).sum());
            index++;
        }
    }
}
