package org.bbelovic.codekatas.gameoflife;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        final Cell [][] initCells = new Cell[][] {
                {new Cell(false), new Cell(false), new Cell(false), new Cell(false)},
                {new Cell(false), new Cell(true), new Cell(true), new Cell(false)},
                {new Cell(false), new Cell(true), new Cell(true), new Cell(false)},
                {new Cell(false), new Cell(false), new Cell(false), new Cell(false)}
        };
        Grid grid = GridSerializer.deserializeFromFile("./blinker.txt");
        Transformer transformer = new Transformer();
        for (int i = 0; i < 10; i++) {
            grid = transformer.transform(grid);
            GridSerializer.serializeToFile(grid, "./blinker-"+ i + ".txt");
        }
        System.out.println(grid);
    }
}
