package org.bbelovic.codekatas.beantostring;


import java.util.Arrays;

class AbstractToStringStrategy implements ToStringStrategy {

    protected String dumpArray(final Object value) {
        String result;
        if (value.getClass() == byte[].class) {
            result = Arrays.toString((byte[]) value);
        }
        else if (value.getClass() == short[].class) {
            result = Arrays.toString((short[]) value);
        }
        else if (value.getClass() == char[].class) {
            result = Arrays.toString((char[]) value);
        }
        else if (value.getClass() == int[].class) {
            result = Arrays.toString((int[]) value);
        }
        else if (value.getClass() == long[].class) {
            result = Arrays.toString((long[]) value);
        }
        else if (value.getClass() == float[].class) {
            result = Arrays.toString((float[]) value);
        }
        else if (value.getClass() == double[].class) {
            result = Arrays.toString((double[]) value);
        }
        else {
            result = Arrays.deepToString((Object[]) value);
        }
        return result;
    }
}
