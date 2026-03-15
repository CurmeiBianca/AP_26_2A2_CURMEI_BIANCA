package org.example.homework;

import java.util.Map;

public interface Profile {

    String getName();

    int getImportance();

    Map<Profile, String> getRelationships();
}