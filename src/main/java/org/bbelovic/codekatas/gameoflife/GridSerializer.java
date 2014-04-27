package org.bbelovic.codekatas.gameoflife;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.String.format;

public class GridSerializer {

    public static void serializeToFile(final Grid grid, final String outputFile) throws IOException {
        final Path outputPath = Paths.get(outputFile);
        final int width = grid.getWidth();
        final int height = grid.getHeight();
        final Collection<String> lines = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            final StringBuilder sb = new StringBuilder();
            for (int j = 0; j < height; j++) {
                final Cell cell = grid.getCellAtPosition(i, j);
                sb.append(format("%s", cell.isAlive() ? "1" : "0"));
            }
            lines.add(sb.toString());
        }
        Files.write(outputPath, lines);
    }

    public static Grid deserializeFromFile(final String inputFile) throws IOException {
        final List<String> lines = Files.readAllLines(Paths.get(inputFile));
        final int height = lines.size();
        final Cell[][] cells = new Cell[height][];
        for (int i = 0; i < height; i++) {
            final String eachLine = lines.get(i);
            checkDimensions(height, eachLine.length());
            cells [i] = parseSingleLine(eachLine);
        }
        return new Grid(cells);
    }

    private static void checkDimensions(final int height, final int width) {
        if (width != height) {
            throw new IllegalArgumentException("Invalid grid format, grid height != grid width");
        }
    }

    private static Cell[] parseSingleLine(final String eachLine) {
        final Cell [] row = new Cell[eachLine.length()];
        for (int j= 0; j < eachLine.length(); j++){
            final String cellValue = eachLine.substring(j, j+1);
            row [j] = new Cell("1".equals(cellValue));
        }
        return row;
    }
}
