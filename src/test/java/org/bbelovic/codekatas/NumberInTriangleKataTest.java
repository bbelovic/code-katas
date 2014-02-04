package org.bbelovic.codekatas;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class NumberInTriangleKataTest {

    private static final String RESOURCES_DIR = "./src/test/resources/";

    @Test(dataProvider = "inputDataAndExpectedResult")
    public void testCompute(final String inputFile, final int expectedResult) throws Exception {
        final Triangle triangle = new TriangleSerializer().deserialize(inputFile);
        final NumberInTriangleKata kata = new NumberInTriangleKata(triangle);
        final int actual = kata.compute();
        assertEquals(actual, expectedResult);
    }

    @DataProvider(name = "inputDataAndExpectedResult")
    public Object [][] computeDataProvider() {
        return new Object [][] {{RESOURCES_DIR + "triangle1.txt", 7},
                                {RESOURCES_DIR + "triangle2.txt", 1},
                                {RESOURCES_DIR + "triangle3.txt", 4}};

    }

}
