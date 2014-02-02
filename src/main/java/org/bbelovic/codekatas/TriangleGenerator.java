package org.bbelovic.codekatas;


import java.util.Random;

public class TriangleGenerator {

    public Triangle generate(final int size) {
        final int [][] triangleData = new int [size][];
        for (int i = 0; i < size; i++) {
            triangleData [i] = generateRandomNumberArray(i+1);
        }
        return new Triangle(triangleData);
    }

    private int [] generateRandomNumberArray(final int length) {
        final Random r = new Random();
        final int [] result = new int [length];
        for (int i = 0; i < length; i++) {
            result [i] = r.nextInt(10);
        }
        return result;
    }
}
