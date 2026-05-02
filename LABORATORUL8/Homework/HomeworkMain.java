package org.example.homework;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HomeworkMain {

    public static void main(String[] args) {
        log.info("Starting Homework application...");
        javax.swing.SwingUtilities.invokeLater(() -> {
            new HomeworkFrame();
        });
    }
}
