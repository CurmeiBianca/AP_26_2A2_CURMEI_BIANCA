package org.example.homework;

import lombok.Data;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Data
public class Company implements Profile {

    private String name;

    private String industry;

    @Setter
    private Map<Profile, String> relationships = new HashMap<>();

    public Company(String name, String industry) {
        this.name = name;
        this.industry = industry;
        this.relationships = new HashMap<>();
    }

    public void addRelationship(Profile target, String type) {

        relationships.put(target, type);
    }

    @Override
    public int getImportance() {

        return relationships.size();
    }

    @Override
    public String toString() {
        return name + " [org.example.homework.Company, industry: " + industry + ", importance: " + getImportance() + "]";
    }
}
