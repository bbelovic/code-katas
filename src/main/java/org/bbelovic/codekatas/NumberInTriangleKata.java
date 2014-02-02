package org.bbelovic.codekatas;

/**
 * As seen on http://www.jroller.com/Solomon/entry/best_tech_interview_question_i
 */
public class NumberInTriangleKata {

    private final Triangle triangle;

    public NumberInTriangleKata(final Triangle triangle) {
        this.triangle = triangle;
    }

    public int compute() {
        int index = 0;
        final int [][] triangleData = triangle.getTriangleData();
        int sum = triangleData[0][0];
        for (int i = 1; i < triangleData.length; i++) {
            final int idx1 = index;
            final int idx2 = index + 1;
            final int [] row = triangleData[i];
            final int val1 = row [idx1];
            final int val2 = row [idx2];
            sum += Math.max(val1, val2);
            index = val1 > val2 ? idx1 : idx2;
        }
        return sum;
    }

}