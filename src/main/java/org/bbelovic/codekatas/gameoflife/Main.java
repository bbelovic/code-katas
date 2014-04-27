package org.bbelovic.codekatas.gameoflife;

import java.io.IOException;

public class Main {
    private static final String BLINKER_PATTERN_FILE = "./src/main/resources/gameoflife/blinker.txt";
    public static void main(String[] args) throws IOException {
        Grid grid = GridSerializer.deserializeFromFile(BLINKER_PATTERN_FILE);
        Transformer transformer = new Transformer();
        for (int i = 0; i < 10; i++) {
            grid = transformer.transform(grid);
            GridSerializer.serializeToFile(grid, "./blinker-"+ i + ".txt");
        }
    }
}
