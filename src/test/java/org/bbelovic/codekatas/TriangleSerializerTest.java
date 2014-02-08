package org.bbelovic.codekatas;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.readAllLines;
import static org.testng.Assert.assertEquals;

@Test
public class TriangleSerializerTest {

    private static final String RESOURCES_DIR = "./src/test/resources/";
    private static final String CORRECT_TRIANGLE = RESOURCES_DIR + "correctTriangleOnInput.txt";
    private static final String MALFORMED_TRIANGLE = RESOURCES_DIR + "malformedTriangleOnInput.txt";
    private static final String ACTUAL_TRIANGLE_FILE = RESOURCES_DIR + "actualTriangle.txt";
    private static final String EXPECTED_TRIANGLE_FILE = RESOURCES_DIR + "expectedTriangle.txt";
    private static final String EMPTY_INPUT_FILE = RESOURCES_DIR + "emptyInputFile.txt";
    private final TriangleSerializer triangleSerializer = new TriangleSerializer();

    @Test
    public void should_serialize_correctly_specified_triangle_into_output_file() throws Exception {
        final Triangle triangle = new Triangle(new int [][] {{0}, {1, 2}, {3, 4, 5}});
        triangleSerializer.serialize(triangle, ACTUAL_TRIANGLE_FILE);
        assertFileContents(ACTUAL_TRIANGLE_FILE, EXPECTED_TRIANGLE_FILE);
    }

    @Test
    public void should_deserialize_correctly_specified_triangle_from_input_file() throws Exception {
        final Triangle actual = triangleSerializer.deserialize(CORRECT_TRIANGLE);
        final int [][] expectedData = new int [][] {{5}, {9, 6}, {4, 6, 8}, {0, 7, 1, 5}};
        final Triangle expected = new Triangle(expectedData);
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void should_throw_exception_for_incorrect_triangle_in_input_file() throws Exception {
        triangleSerializer.deserialize(MALFORMED_TRIANGLE);
    }

    @Test(expectedExceptions = RuntimeException.class,
          expectedExceptionsMessageRegExp = "Empty input file")
    public void should_throw_exception_when_input_file_is_empty() throws IOException {
        triangleSerializer.deserialize(EMPTY_INPUT_FILE);
    }
    @AfterMethod
    public void tearDown() throws Exception {
        Files.deleteIfExists(Paths.get(ACTUAL_TRIANGLE_FILE));
    }

    private void assertFileContents(final String fileName1, final String fileName2) throws Exception{
        final Path path1 = Paths.get(fileName1);
        final Path path2 = Paths.get(fileName2);
        final List<String> contents1 = readAllLines(path1, UTF_8);
        final List<String> contents2 = readAllLines(path2, UTF_8);
        assertEquals(contents1, contents2);
    }
}
