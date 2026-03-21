package org.example.compulsory.catalog.repository;

import org.example.compulsory.catalog.exceptions.InvalidResourceException;
import org.example.compulsory.catalog.exceptions.ResourceNotFoundException;
import org.example.compulsory.catalog.model.Resource;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;

import java.util.HashMap;
import java.util.Map;

public class Catalog {

    private final Map<String, Resource> resources = new HashMap<>();

    public void addResource(Resource resource) throws InvalidResourceException {
        if (resource.getId() == null || resource.getId().isEmpty()) {
            throw new InvalidResourceException("Resource ID cannot be null or empty.");
        }
        resources.put(resource.getId(), resource);
    }

    public Resource getResource(String id) throws ResourceNotFoundException {
        if (!resources.containsKey(id)) {
            throw new ResourceNotFoundException("Resource with ID " + id + " not found.");
        }
        return resources.get(id);
    }

    public void openResource(String id) throws Exception {
        Resource resource = getResource(id);

        Desktop desktop = Desktop.getDesktop();

        if (resource.getLocation().startsWith("http")) {
            desktop.browse(new URI(resource.getLocation()));
        } else {
            File file = new File(resource.getLocation());
            if (!file.exists()) {
                throw new ResourceNotFoundException("File not found: " + resource.getLocation());
            }
            desktop.open(file);
        }
    }
}
