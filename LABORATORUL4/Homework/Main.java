package org.example.homework;

import org.example.compulsory.Intersection;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Main {
    public static void main(String [] args) {

        City city = City.generateRandomCity(10, 15);

        System.out.println("=== ORAS GENERAT RANDOM ===");
        city.printCityInfo();

        System.out.println("\n=== LISTA INTERSECTII ===");
        city.getIntersections().forEach(System.out::println);

        System.out.println("\n=== LISTA STRAZI ===");
        city.getStreets().forEach(System.out::println);

        System.out.println("\n=== STRAZI MAI LUNGI DE 7 SI CU GRAD >= 3 ===");
        city.getStreetsLongerThanAndWidthHighDegree(7, 3)
                .forEach(System.out::println);

        System.out.println("\n=== PRIMELE 3 MST-URI ORDONATE DUPA COST ===");
        var mstList = city.getKBestMSTs(3);

        int index = 1;
        for (Graph<Intersection, DefaultWeightedEdge> mst : mstList) {
            System.out.println("\nMST #" + index);
            System.out.println("Cost total: " + mst.edgeSet().stream()
                    .mapToDouble(mst::getEdgeWeight)
                    .sum());

            System.out.println("Muchii:");
            mst.edgeSet().forEach(edge -> {
                Intersection a = mst.getEdgeSource(edge);
                Intersection b = mst.getEdgeTarget(edge);
                double weight = mst.getEdgeWeight(edge);
                System.out.println(a + " -- " + b + " (cost " + weight + ")");
            });

            index++;
        }
    }
}
