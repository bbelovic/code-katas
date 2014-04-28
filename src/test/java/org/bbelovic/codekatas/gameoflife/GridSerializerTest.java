package org.bbelovic.codekatas.gameoflife;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;

@Test
public class GridSerializerTest {

    private static final String TEST_RESOURCES_DIR = "./src/test/resources/gameoflife";
    private static final String FILENAME = TEST_RESOURCES_DIR + "/grid.txt";
    private static final String VALID_GRID = TEST_RESOURCES_DIR + "/validGrid.txt";
    private static final String INVALID_GRID = TEST_RESOURCES_DIR + "/invalidGrid.txt";
    private GridSerializer gridSerializer;

    @BeforeMethod
    public void setUp() throws IOException {
        gridSerializer = new GridSerializer();
        Files.deleteIfExists(Paths.get(FILENAME));

    }

    @Test
    public void should_serialize_grid_into_output_file() throws Exception {
        final Cell [][] initCells = new Cell[][] {
                {new Cell(false), new Cell(false), new Cell(false), new Cell(false)},
                {new Cell(false), new Cell(true), new Cell(true), new Cell(false)},
                {new Cell(false), new Cell(true), new Cell(true), new Cell(false)},
                {new Cell(false), new Cell(false), new Cell(false), new Cell(false)}
        };
        final Grid grid = new Grid(initCells);
        gridSerializer.serializeToFile(grid, FILENAME);
        final List<String> lines = Files.readAllLines(Paths.get(FILENAME));
        assertEquals(lines, asList("0000", "0110", "0110", "0000"));
    }

    @Test
    public void should_deserialize_grid_from_output_file() throws IOException {
        final Grid actual = gridSerializer.deserializeFromFile(VALID_GRID);
        final Grid expected = new Grid(new Cell[][] {{new Cell(false), new Cell(false)},
                                                     {new Cell(true), new Cell(true)}});
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void should_throw_exception_when_input_file_has_invalid_format() throws IOException {
        gridSerializer.deserializeFromFile(INVALID_GRID);
    }
}
