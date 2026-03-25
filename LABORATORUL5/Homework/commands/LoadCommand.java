package org.example.homework.commands;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.example.homework.repository.Catalog;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

@Data
@AllArgsConstructor
public class LoadCommand implements Command {

    private String path;

    @Override
    public void execute(Catalog catalog) throws Exception {

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {

            // Citim obiectul serializat din fisier si il convertim in Catalog
            Catalog loaded = (Catalog) in.readObject();

            catalog.copyFrom(loaded);

            System.out.println("Catalog incarcat din: " + path);
        }
    }
}
