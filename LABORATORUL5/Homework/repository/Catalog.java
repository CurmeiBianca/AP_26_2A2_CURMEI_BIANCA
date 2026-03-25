package org.example.homework.repository;

import lombok.Getter;

import org.example.compulsory.catalog.exceptions.InvalidResourceException;
import org.example.compulsory.catalog.exceptions.ResourceNotFoundException;
import org.example.homework.model.Resource;

import java.io.Serializable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Catalog implements Serializable {

    // Identificator unic al versiunii clasei pentru mecanismul de serailizare
    // Asigura compatibilitatea intre versiunea serializata si cea deserializata
    private static final long serialVersionUID = 1L;

    @Getter
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

    public Collection<Resource> getAllResources() {
        return resources.values();
    }

    public void copyFrom(Catalog other) {
        this.resources.clear();
        this.resources.putAll(other.resources);
    }
}