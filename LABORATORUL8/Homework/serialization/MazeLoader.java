package org.example.homework.serialization;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

@UtilityClass
public class MazeLoader {

    public boolean[][][] load(File file) {
        if (file == null)
            return null;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            // Citim obiectul si il convertim la tipul corect
            return (boolean[][][]) in.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
