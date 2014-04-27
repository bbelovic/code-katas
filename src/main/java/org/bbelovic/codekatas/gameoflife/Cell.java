package org.bbelovic.codekatas.gameoflife;

import static java.lang.String.format;

public final class Cell {
    private final boolean alive;

    public Cell(final boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (alive != cell.alive) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (alive ? 1 : 0);
    }

    @Override
    public String toString() {
        return format("Cell[alive=%s]", alive);
    }
}
