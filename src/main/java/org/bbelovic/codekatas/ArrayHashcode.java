package org.bbelovic.codekatas;

import java.util.Arrays;

/**
 * Created by bbelovic on 2/2/14.
 */
public class ArrayHashcode {
    public static void main(String[] args) {
        final int [][] arr1 = new int [][] {{1}, {2}};
        final int [][] arr2 = new int [][] {{1}, {2}};

        final int [] arr3 = new int [] {0, 0};
        final int [] arr4 = new int [] {0, 0};

        hashCode(arr1, arr2);
        System.out.println();
        hashCode(arr3, arr4);
    }

    private static void hashCode(int[][] arr1, int[][] arr2) {
        System.out.println("arr1.hashCode()="+ arr1.hashCode());
        System.out.println("arr2.hashCode()="+ arr2.hashCode());
        System.out.println("Arrays.hashCode(arr1)="+ Arrays.hashCode(arr1));
        System.out.println("Arrays.hashCode(arr2)="+ Arrays.hashCode(arr2));
        System.out.println("Arrays.deepHashCode(arr1)="+ Arrays.deepHashCode(arr1));
        System.out.println("Arrays.deepHashCode(arr2)="+ Arrays.deepHashCode(arr2));
    }

    private static void hashCode(int [] arr1, int [] arr2) {
        System.out.println("arr1.hashCode()="+ arr1.hashCode());
        System.out.println("arr2.hashCode()="+ arr2.hashCode());
        System.out.println("Arrays.hashCode(arr1)="+ Arrays.hashCode(arr1));
        System.out.println("Arrays.hashCode(arr2)="+ Arrays.hashCode(arr2));
    }
}
