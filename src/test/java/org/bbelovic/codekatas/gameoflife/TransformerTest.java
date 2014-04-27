package org.bbelovic.codekatas.gameoflife;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class TransformerTest {

    private static final Transformer TRANSFORMER = new Transformer();

    @Test
    public void dead_cell_should_become_live_cell_if_it_has_exactly_three_live_neighbours() {
        final Grid in = new Grid(CELLS_1);
        final Grid out = TRANSFORMER.transform(in);
        assertEquals(out, new Grid(CELLS_2));
    }

    @Test
    public void live_cell_should_become_dead_cell_when_it_has_less_than_two_living_neighbours() {
        final Grid in = new Grid(CELLS_3);
        final Grid out = TRANSFORMER.transform(in);
        assertEquals(out, new Grid(CELLS_4));
    }

    @Test
    public void live_cell_should_remain_live_cell_when_it_has_two_or_three_living_neighbours() {
        final Grid in = new Grid(CELLS_5);
        final Grid out = TRANSFORMER.transform(in);
        assertEquals(out, new Grid(CELLS_6));
    }

    @Test
    public void live_cell_should_die_when_it_has_more_than_three_living_neighbours() {
        final Grid in = new Grid(CELLS_7);
        final Grid out = TRANSFORMER.transform(in);
        assertEquals(out, new Grid(CELLS_8));
    }

    private static final Cell [][] CELLS_1 = new Cell[][] {
            {new Cell(true), new Cell(false), new Cell(true)},
            {new Cell(false), new Cell(false), new Cell(false)},
            {new Cell(true), new Cell(false), new Cell(false)}
    };

    private static final Cell [][] CELLS_2 = new Cell[][] {
            {new Cell(false), new Cell(false), new Cell(false)},
            {new Cell(false), new Cell(true), new Cell(false)},
            {new Cell(false), new Cell(false), new Cell(false)}
    };

    private static final Cell [][] CELLS_3 = new Cell[][] {
            {new Cell(true), new Cell(true), new Cell(false)},
            {new Cell(false), new Cell(false), new Cell(false)},
            {new Cell(false), new Cell(false), new Cell(false)}
    };
    private static final Cell [][] CELLS_4 = new Cell[][] {
            {new Cell(false), new Cell(false), new Cell(false)},
            {new Cell(false), new Cell(false), new Cell(false)},
            {new Cell(false), new Cell(false), new Cell(false)}
    };

    private static final Cell [][] CELLS_5 = new Cell[][] {
            {new Cell(true), new Cell(true), new Cell(false)},
            {new Cell(true), new Cell(false), new Cell(false)},
            {new Cell(false), new Cell(false), new Cell(false)}
    };

    private static final Cell [][] CELLS_6 = new Cell[][] {
            {new Cell(true), new Cell(true), new Cell(false)},
            {new Cell(true), new Cell(true), new Cell(false)},
            {new Cell(false), new Cell(false), new Cell(false)}
    };

    private static final Cell [][] CELLS_7 = new Cell[][] {
            {new Cell(false), new Cell(true), new Cell(true)},
            {new Cell(false),  new Cell(true), new Cell(false)},
            {new Cell(true), new Cell(false), new Cell(true)}
    };

    private static final Cell [][] CELLS_8 = new Cell[][] {
            {new Cell(false),  new Cell(true),  new Cell(true)},
            {new Cell(true),  new Cell(false),  new Cell(false)},
            {new Cell(false),   new Cell(true), new Cell(false)}
    };

}
