package org.example.homework;

import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
public abstract class Person implements Profile {

    private String name;

    private LocalDate birthDate;

    @Setter
    private Map<Profile, String> relationships;

    public Person(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
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

        return name + " (birth: " + birthDate + ", importance: " + getImportance() + ")";
    }
}
