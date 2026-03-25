package org.example.homework.commands;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.example.homework.repository.Catalog;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

@Data
@AllArgsConstructor
public class SaveCommand implements Command {

    private String path;

    @Override
    public void execute(Catalog catalog) throws Exception {

        // ObjectOutputStream permite scrierea unui obiect Java în format binar
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {

            // Serializam obiectul catalog si il scriem in fisierul specificat
            out.writeObject(catalog);

            System.out.println("Catalog salvat in: " + path);
        }
    }
}
