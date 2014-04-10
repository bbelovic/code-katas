package org.bbelovic.codekatas.beantostring;

public class ArrayBean {

    private static final int INT_CONSTANT = 0;
    private final int [] intArr;

    private final int [][] intMatrix;
    private final Object [] objectArr;
    private final Object [][] objectMatrix;
    private final Object someObject;

    public ArrayBean(final int[] intArr, final int[][] intMatrix,
                     final Object[] objectArr, final Object[][] objectMatrix,
                     final Object someObject) {
        this.intArr = intArr;
        this.intMatrix = intMatrix;
        this.objectArr = objectArr;
        this.objectMatrix = objectMatrix;
        this.someObject = someObject;
    }

    public int[] getIntArr() {
        return intArr;
    }

    public int[][] getIntMatrix() {
        return intMatrix;
    }

    public Object[] getObjectArr() {
        return objectArr;
    }

    public Object[][] getObjectMatrix() {
        return objectMatrix;
    }

    public Object getSomeObject() {
        return someObject;
    }

    @Override
    public String toString() {
        return new BeanToStringBuilder(this, new DirectFieldAccessToStringStrategy(this)).toString();
    }


}
