package org.example.homework.util;

import lombok.experimental.UtilityClass;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@UtilityClass
public class MazeExporter {

    public void exportPNG(BufferedImage image, File file) {
        if (image == null || file == null)
            return;

        try {
            // Scriem imaginea in format PNG
            ImageIO.write(image, "png", file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
