package com.library.algorithms.arrays;


import java.util.ArrayList;
import java.util.List;

public class ReverseSentenceOfCharArray {

    public static char[] reverseArrayWithoutTempArray(char[] array) {
        int i = 0, j = array.length - 1;
        for (i = 0; i < array.length / 2; i++, j--) {
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return array;
    }

    public static char[] reverseSentenceArray(char[] array) {

        if (array == null) {
            throw new IllegalArgumentException("Char Array cannot be null !!");
        }

        List<Integer> startPointer = new ArrayList<>();
        List<Integer> endPointer = new ArrayList<>();
        int count = 0;
        boolean isWord = false;

        for (char ch : array) {
            if (ch == ' ' && isWord) {
                endPointer.add(count);
                isWord = false;
            } else {
                if (!isWord) {
                    startPointer.add(count);
                    isWord = true;
                }
            }

            count++;

            if (isWord && count >= array.length) {
                endPointer.add(count);
            }
        }

        char[] result = new char[array.length];
        int c = 0;
        for (int j = startPointer.size() - 1; j >= 0; j--) {
            for (int k = startPointer.get(j); k < endPointer.get(j); k++) {
                result[c++] = array[k];
            }

            if (c < array.length) {
                result[c++] = ' ';
            }
        }

        return result;
    }

    private static void printCharArray(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            System.out.print("'" + chars[i] + "'");
            if (i + 1 < chars.length) {
                System.out.print(", ");
            }
        }
    }

    public static void main(String[] args) {
        char[] input = {'o', 'n', 'e', ' ', 't', 'w', 'o', ' ', 't', 'h', 'r', 'e', 'e'};
        printCharArray(reverseSentenceArray(input));
    }
}
