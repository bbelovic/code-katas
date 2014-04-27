package org.bbelovic.codekatas.gameoflife;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;
import static java.util.Objects.deepEquals;

public class Grid {
    private final Cell [][] cells;

    public Grid(final Cell[][] cells) {
        this.cells = cells;
    }

    public Cell getCellAtPosition(final int x, final int y) {
        rangeCheck(x, y);
        return new Cell(cells[x][y].isAlive());
    }

    public List<Cell> getNeighboursForCell(final int x, final int y) {
        int [][] delta = new int [][] {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
        final List<Cell> result = new ArrayList<>();
        for (int [] each: delta) {
            int posX = x + each[0];
            int posY = y + each[1];
            if (isValidCellPosition(posX, posY)) {
                result.add(getCellAtPosition(posX, posY));
            }
        }
        return result;
    }

    public int getNumberOfLivingNeighboursForCell(int x, int y) {
        return (int) getNeighboursForCell(x, y).stream().filter(cell->cell.isAlive()).count();
    }

    private void rangeCheck(int x, int y) {
        if (x < 0 || x > cells.length - 1) {
            throw new IllegalArgumentException();

        }
        if (y < 0 || y > cells[0].length - 1) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isValidCellPosition(final int x, final int y) {
        return ((x >= 0 && x <= cells.length - 1) && (y >= 0 && y <= cells[0].length - 1));
    }

    public int getWidth() {
        return cells.length;
    }

    public int getHeight() {
        return cells[0].length;
    }

    public void setCellAtPosition(final int x, final int y, final Cell cell) {
        rangeCheck(x, y);
        cells[x][y] = new Cell(cell.isAlive());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Grid other = (Grid)o;
        return deepEquals(cells, other.cells);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(cells);
    }

    @Override
    public String toString() {
        return format("Grid[cells=%s]", Arrays.deepToString(cells));
    }
}
