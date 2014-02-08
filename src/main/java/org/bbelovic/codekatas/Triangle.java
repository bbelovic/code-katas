package org.bbelovic.codekatas;

import java.util.Arrays;

import static java.lang.String.format;

/**
 * This class represents triangle constructed out of integers.
 */
public class Triangle {

    private final int [][] triangleData;

    public Triangle(final int[][] triangleData) {
        if (triangleData == null) {
            throw new IllegalArgumentException("triangle data is null");
        }
        this.triangleData = triangleData;
    }

    public int [][] getTriangleData() {
        return copyTriangleData();
    }

    public int getSize() {
        return triangleData.length;
    }

    public int getElementAtPosition(final int row, final int elementIndex) {
        rangeCheck(row, elementIndex);
        return triangleData [row][elementIndex];
    }

    private void rangeCheck(final int row, final int elementIndex) {
        if (row >= triangleData.length || row < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (elementIndex > triangleData[row].length - 1 || elementIndex < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Triangle)) return false;
        return Arrays.deepEquals(triangleData, ((Triangle) o).triangleData);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(triangleData);
    }

    @Override
    public String toString() {
        return format("Triangle[triangleData=%s]", Arrays.deepToString(triangleData));
    }

    private int [][] copyTriangleData() {
        final int [][] result = new int [triangleData.length][];
        for (int i = 0; i < result.length; i++) {
            result [i] = new int[triangleData[i].length];
            System.arraycopy(triangleData[i], 0, result[i], 0, triangleData[i].length);
        }
        return result;
    }

}
