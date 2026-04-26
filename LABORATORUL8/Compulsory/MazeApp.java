package org.example.compulsory;

import org.example.compulsory.ui.MainFrame;

import javax.swing.*;

public class MazeApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
