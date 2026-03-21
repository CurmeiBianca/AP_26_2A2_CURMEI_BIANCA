package org.example.compulsory.catalog.app;

import org.example.compulsory.catalog.exceptions.InvalidResourceException;
import org.example.compulsory.catalog.repository.Catalog;
import org.example.compulsory.catalog.model.Resource;

public class Main {
    public static void main(String[] args) {

        Catalog catalog = new Catalog();

        try {
            catalog.addResource(new Resource(
                    "knuth67",
                    "The Art of Computer Programming",
                    "d:/books/programming/tacp.ps",
                    "1967",
                    "Donald E. Knuth"
            ));

            catalog.addResource(new Resource(
                    "jvm25",
                    "The Java Virtual Machine Specification",
                    "https://docs.oracle.com/javase/specs/jvms/se25/html/index.html",
                    "2025",
                    "Tim Lindholm & others"
            ));

            catalog.openResource("jvm25");

        } catch (InvalidResourceException e) {
            System.err.println("Invalid resource: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error opening resource: " + e.getMessage());
        }
    }
}
