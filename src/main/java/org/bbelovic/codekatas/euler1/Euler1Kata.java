package org.bbelovic.codekatas.euler1;

public class Euler1Kata {
    public int compute(final int max) {
        int result = 0;
        for (int i = 1; i < max; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                result += i;
            }
        }
        return result;
    }
}
