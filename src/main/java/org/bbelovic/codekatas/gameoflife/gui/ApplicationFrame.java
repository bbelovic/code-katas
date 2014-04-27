package org.bbelovic.codekatas.gameoflife.gui;

import org.bbelovic.codekatas.gameoflife.Cell;
import org.bbelovic.codekatas.gameoflife.Grid;
import org.bbelovic.codekatas.gameoflife.GridSerializer;

import javax.swing.*;
import java.io.IOException;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class ApplicationFrame {
    private JFrame frame;

    public ApplicationFrame() {

        init();
    }

    private void init() {
        frame = new JFrame();
        frame.setBounds(0, 0, 402, 433);
        final Grid grid = readInputGrid();
        frame.add(new DrawingPanel(grid));
        frame.setTitle("Conway's game of life");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private Grid readInputGrid() {
        try {
//            return GridSerializer.deserializeFromFile("./src/main/resources/gameoflife/blinker.txt");
//            return GridSerializer.deserializeFromFile("./src/main/resources/gameoflife/beacon.txt");
            return GridSerializer.deserializeFromFile("./src/main/resources/gameoflife/octagon4.txt");
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return new Grid(new Cell[][] {{}});
    }

    public static void main( String[] args )
    {
        final Runnable doRun = () -> {new ApplicationFrame();};
        /*
         * Runs GUI safely on AWT event dispatching thread.
         */
        SwingUtilities.invokeLater(doRun);
    }
}
