package org.example.homework.serialization;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

@UtilityClass
public class MazeSerializer {

    public void save(boolean[][][] walls, File file) {
        if (walls == null || file == null)
            return;

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(walls);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
