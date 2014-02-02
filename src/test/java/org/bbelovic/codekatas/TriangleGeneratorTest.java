package org.bbelovic.codekatas;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Test
public class TriangleGeneratorTest {

    private static final int SIZE = 6;

    @Test
    public void should_generate_triangle_with_given_size_filled_with_random_numbers() {
        final TriangleGenerator triangleGenerator = new TriangleGenerator();
        final Triangle actualTriangle = triangleGenerator.generate(SIZE);
        assertTriangle(actualTriangle);
    }

    private void assertTriangle(final Triangle actualTriangle) {
        final int [][] triangleData = actualTriangle.getTriangleData();
        assertEquals(triangleData.length, SIZE);
        for (int i = 0; i < SIZE; i++) {
            assertEquals(triangleData[i].length, i + 1);
            assertTriangleRowData(triangleData[i]);
        }
    }

    private void assertTriangleRowData(final int[] triangleRowData) {
        for (final int aTriangleRowData : triangleRowData) {
            assertTrue(aTriangleRowData < 10 && aTriangleRowData >= 0);
        }
    }
}
