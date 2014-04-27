package org.bbelovic.codekatas.gameoflife;

public class Transformer {
    public Grid transform(final Grid in) {
        final int width = in.getWidth();
        final int height = in.getHeight();
        final Grid out = new Grid(new Cell[width][height]);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                final Cell cell = in.getCellAtPosition(i, j);
                final int livingCells = in.getNumberOfLivingNeighboursForCell(i, j);
                if (!cell.isAlive()) {
                    if (livingCells == 3) {
                        out.setCellAtPosition(i, j, new Cell(true));
                    } else {
                        out.setCellAtPosition(i, j, cell);
                    }
                } else {
                    if (livingCells < 2) {
                        out.setCellAtPosition(i,j, new Cell(false));
                    } else if (livingCells > 3) {
                        out.setCellAtPosition(i, j, new Cell(false));
                    } else if (livingCells == 2 || livingCells == 3){
                        out.setCellAtPosition(i, j, new Cell(true));
                    } else {
                        out.setCellAtPosition(i, j, cell);
                    }
                }
            }
        }
        return out;
    }
}
