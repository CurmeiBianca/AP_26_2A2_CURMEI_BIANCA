package org.example.homework.app;

import org.example.homework.model.Resource;
import org.example.homework.commands.*;
import org.example.homework.repository.Catalog;

public class Main {
    public static void main(String[] args) {
        try {
            Catalog catalog = new Catalog();

            catalog.addResource(new Resource(
                    "1",
                    "Clean Code",
                    "C:/Users/Curmei/Documents/clean_code.pdf",
                    "2008",
                    "Robert C. Martin"));

            catalog.addResource(new Resource(
                    "2",
                    "Spring Documentation",
                    "https://spring.io/projects/spring-framework",
                    "2004",
                    "Pivotal"));

            catalog.addResource(new Resource(
                    "3",
                    "Java Language Specification",
                    "C:/Users/Curmei/Documents/jls.pdf",
                    "2023",
                    "Oracle"));

            System.out.println("=== LISTARE RESURSE ===");
            new ListCommand().execute(catalog);

            new SaveCommand("catalog.ser").execute(catalog);

            Catalog loadedCatalog = new Catalog();
            new LoadCommand("catalog.ser").execute(loadedCatalog);

            System.out.println("\n=== LISTARE DUPA INCARCARE ===");
            new ListCommand().execute(loadedCatalog);

            new ReportCommand("report.html").execute(loadedCatalog);
        } catch (Exception except) {
            except.printStackTrace();
        }
    }
}
