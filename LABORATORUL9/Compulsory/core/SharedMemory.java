package org.example.compulsory.core;

import java.util.ArrayList;
import java.util.List;

public class SharedMemory {

    private final List<String> messages = new ArrayList<>();

    public synchronized void write(String message) {
        messages.add(message);
        System.out.println("[SharedMemory] " + message);
    }

    public synchronized List<String> readAll() {
        return new ArrayList<>(messages);
    }
}
