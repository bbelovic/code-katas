package org.bbelovic.codekatas.gameoflife;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.testng.Assert.*;

@Test
public class GridTest {

    @Test
    public void should_return_width_of_the_grid() {
        final Grid grid = new Grid(CELLS);
        final int width = grid.getWidth();
        assertEquals(width, CELLS.length);
    }

    @Test
    public void should_return_height_of_the_grid() {
        final Grid grid = new Grid(CELLS);
        final int height = grid.getHeight();
        assertEquals(height, CELLS[0].length);
    }

    @Test
    public void should_return_cell_at_requested_position() {
        final Grid grid = new Grid(getCells());
        final Cell actual = grid.getCellAtPosition(0, 1);
        assertNotNull(actual);
        assertTrue(actual.isAlive());
    }

    @Test
    public void should_set_cell_at_requested_position() {
        final Grid grid = new Grid(getCells());
        Cell actual = grid.getCellAtPosition(0, 1);
        assertTrue(actual.isAlive());
        final Cell cellToBeSet = new Cell(false);
        grid.setCellAtPosition(0, 1, cellToBeSet);
        actual = grid.getCellAtPosition(0, 1);
        assertFalse(actual.isAlive());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void should_throw_exception_when_cell_at_invalid_position_is_requested() {
        final Grid grid = new Grid(getCells());
        grid.getCellAtPosition(-2, 13);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void should_throw_exception_when_new_cell_is_being_set_on_invalid_position() {
        final Grid grid = new Grid(getCells());
        grid.setCellAtPosition(-2, 10, new Cell(false));
    }

    @Test(dataProvider = "neighbouringCellProvider")
    public void should_return_all_neighbours_for_cell(final int [] coords, final List<Cell> expectedCells) {
        final Grid grid = new Grid(CELLS);
        final List<Cell> actual = grid.getNeighboursForCell(coords[0], coords[1]);
        assertEquals(actual, expectedCells);
    }

    @Test
    public void should_return_number_of_living_neighbours_for_cell() {
        final Grid grid = new Grid(CELLS);
        int actual = grid.getNumberOfLivingNeighboursForCell(0, 0);
        assertEquals(actual, 1);
        actual = grid.getNumberOfLivingNeighboursForCell(0, 1);
        assertEquals(actual, 0);
        actual = grid.getNumberOfLivingNeighboursForCell(0, 2);
        assertEquals(actual, 1);
        actual = grid.getNumberOfLivingNeighboursForCell(0, 3);
        assertEquals(actual, 0);
    }

    @Test
    public void equals_implementation_should_be_reflexive() {
        final Grid grid = new Grid(CELLS);
        assertTrue(grid.equals(grid));
    }

    @Test
    public void equals_implementation_should_be_symmetric() {
        final Grid grid1 = new Grid(CELLS);
        final Grid grid2 = new Grid(CELLS);
        assertTrue(grid1.equals(grid2));
        assertTrue(grid2.equals(grid1));
    }

    @Test
    public void equals_implementation_should_be_transitive() {
        final Grid grid1 = new Grid(CELLS);
        final Grid grid2 = new Grid(CELLS);
        final Grid grid3 = new Grid(CELLS);
        assertTrue(grid1.equals(grid2));
        assertTrue(grid2.equals(grid3));
        assertTrue(grid1.equals(grid3));
    }

    @Test
    public void should_return_same_hashcode_value_for_instances_that_are_equal_according_equals() {
        final Grid grid1 = new Grid(CELLS);
        final Grid grid2 = new Grid(CELLS);
        if (grid1.equals(grid2)) {
            assertEquals(grid1.hashCode(), grid2.hashCode());
        } else {
            fail("Two instances that are equal, should produce same hashCode value");
        }
    }

    private Cell[][] getCells() {
        return new Cell[][] {{new Cell(false), new Cell(true)},
                {new Cell(false), new Cell(false)}};
    }

    private static final Cell [][] CELLS = {
            {new Cell(false), new Cell(true), new Cell(false), new Cell(false)},
            {new Cell(false), new Cell(false), new Cell(false), new Cell(false)},
            {new Cell(false), new Cell(false), new Cell(false), new Cell(true)},
            {new Cell(false), new Cell(false), new Cell(false), new Cell(false)}
    };

    @DataProvider(name = "neighbouringCellProvider")
    private Object[][] neighbouringCellProvider() {
        return new Object[][] {
                {new int [] {0, 0}, asList(new Cell(true), new Cell(false), new Cell(false))},
                {new int [] {0, 1}, asList(new Cell(false), new Cell(false), new Cell(false), new Cell(false), new Cell(false))},
                {new int [] {0, 2}, asList(new Cell(true), new Cell(false), new Cell(false), new Cell(false), new Cell(false))},
                {new int [] {0, 3}, asList(new Cell(false), new Cell(false), new Cell(false))},

                {new int [] {1, 0}, asList(new Cell(false), new Cell(true), new Cell(false), new Cell(false), new Cell(false))},
                {new int [] {1, 1}, asList(new Cell(false), new Cell(true), new Cell(false),
                        new Cell(false), new Cell(false), new Cell(false), new Cell(false), new Cell(false))},
                {new int [] {1, 2}, asList(new Cell(true), new Cell(false), new Cell(false),
                                           new Cell(false), new Cell(false),
                                           new Cell(true), new Cell(false), new Cell(false))},
                {new int [] {1, 3}, asList(new Cell(false), new Cell(false), new Cell(false), new Cell(true), new Cell(false))},


                {new int [] {3, 3}, asList(new Cell(false), new Cell(true), new Cell(false))}};
    }
}
