package org.example.homework.commands;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.example.compulsory.catalog.exceptions.ResourceNotFoundException;
import org.example.homework.model.Resource;

import org.example.homework.repository.Catalog;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;

@Data
@AllArgsConstructor
public class ViewCommand implements Command {

    private String resourceId;

    @Override
    public void execute(Catalog catalog) throws Exception {

        Resource resource = catalog.getResource(resourceId);

        Desktop desktop = Desktop.getDesktop();

        String location = resource.getLocation();

        if (location.startsWith("https")) {

            desktop.browse(new URI(location));
        } else {

            File file = new File(location);

            if (!file.exists()) {
                throw new ResourceNotFoundException("Fisierul nu exista: " + location);
            }

            desktop.open(file);
        }
    }
}
