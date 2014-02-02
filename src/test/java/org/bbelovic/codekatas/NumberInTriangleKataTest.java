package org.bbelovic.codekatas;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class NumberInTriangleKataTest {

    @Test
    public void testCompute() throws Exception {
        final Triangle triangle = new TriangleSerializer().deserialize("./src/test/resources/triangle.txt");
        final NumberInTriangleKata kata = new NumberInTriangleKata(triangle);
        final int actual = kata.compute();
        assertEquals(actual, 27);
    }

}
