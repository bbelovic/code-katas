package org.bbelovic.codekatas;

/**
 * As seen on http://www.jroller.com/Solomon/entry/the_quick_no_in_tech
 */
public class SimpleStringKata {
    public String reverse(String input) {
        char [] arr = input.toCharArray();
        for (int i = 0; i < arr.length / 2; i++) {
            char c = arr [i];
            arr [i] = arr [arr.length - i - 1];
            arr [arr.length - i - 1] = c;
        }
        return String.valueOf(arr);
    }

    public String copyForward(final String input) {
        if (input.isEmpty()) return input;
        char [] arr = input.toCharArray();
        char c = arr[0];
        for (int i = 1; i < arr.length; i++) {
            char c1 = arr[i];
            arr [i] = c;
            c = c1;
        }
        return String.valueOf(arr);
    }



}
