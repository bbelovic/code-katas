package org.bbelovic.codekatas.gameoflife.gui;

import org.bbelovic.codekatas.gameoflife.Cell;
import org.bbelovic.codekatas.gameoflife.Grid;
import org.bbelovic.codekatas.gameoflife.Transformer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

public class DrawingPanel extends JPanel implements ActionListener {

    private static final int SQUARE_SIZE = 20;
    private static final int VERTICAL_OFFSET = 1 * SQUARE_SIZE;
    private static final int HORIZONTAL_OFFSET = 1 * SQUARE_SIZE;

    private Transformer transformer = new Transformer();
    private Grid grid;
    private Timer timer;

    public DrawingPanel(Grid grid) {
        this.grid = grid;
        this.timer = new Timer(1000, this);
        timer.start();
    }

    private static final Grid GRID = new Grid(new Cell[][] {{new Cell(false), new Cell(true)},
            {new Cell(true), new Cell(false)}});

    @Override
    public void paintComponent(final Graphics g) {
        paintBackground(g);
        paintGrid(grid, g);
    }

    private void paintBackground(final Graphics g) {
        g.setColor(WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(BLACK);
        for (int i = 0; i < getWidth(); i += SQUARE_SIZE) {
            g.drawLine(i ,0, i, getHeight());
            g.drawLine(0 ,i, getWidth(), i);
        }
    }

    private void paintGrid(final Grid grid, final Graphics g) {
        g.setColor(BLACK);
        for (int i = 0; i < grid.getWidth(); i++) {
            for (int j = 0; j < grid.getHeight(); j++) {
                if (grid.getCellAtPosition(i, j).isAlive()) {
                    g.fillRect(HORIZONTAL_OFFSET + i*SQUARE_SIZE, VERTICAL_OFFSET + j*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        grid = transformer.transform(grid);
        repaint();
    }
}
