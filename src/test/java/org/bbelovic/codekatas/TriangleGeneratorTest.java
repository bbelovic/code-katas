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
        assertEquals(actualTriangle.getSize(), SIZE);
        int rowLength = 1;
        int elementCount = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < rowLength; j++) {
                final int element = actualTriangle.getElementAtPosition(i, j);
                assertTrue(element < 10 && element >= 0);
                elementCount++;
            }
            ++rowLength;
        }
        assertEquals(elementCount, computeElementCount(SIZE));
    }

    private int computeElementCount(final int size) {
        int result = 0;
        for (int i = size; i > 0; i--) {
            result += i;
        }
        return result;
    }
}
