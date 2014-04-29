package org.bbelovic.codekatas.gameoflife.gui;

import org.bbelovic.codekatas.gameoflife.Grid;

import javax.swing.*;

import static java.util.Objects.requireNonNull;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class ApplicationFrame {
    private static final int WIDTH = 402;
    private static final int HEIGHT = 433;
    private final Grid grid;

    public ApplicationFrame(final Grid grid) {
        this.grid = requireNonNull(grid, "Input grid can't be null");
    }


    public void createAndShowGui() {
        final JFrame frame = new JFrame();
        frame.setBounds(0, 0, WIDTH, HEIGHT);
        final DrawingPanel drawingPanel = new DrawingPanel(grid);
        frame.add(drawingPanel);
        frame.setTitle("Conway's game of life");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
