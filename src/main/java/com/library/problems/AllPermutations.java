package com.library.problems;

public class AllPermutations {

    /* Function to print permutations of string
   This function takes three parameters:
   1. String
   2. Starting index of the string
   3. Ending index of the string. */
    public static void permute(char[] a, int i, int n) {
        int j;

        if (i == n) {
            System.out.println(a);
        } else {
            for (j = i; j <= n; j++) {
                swap(a, i, j);
                permute(a, i + 1, n);
                swap(a, i, j); //backtrack
            }
        }
    }

    /* Function to swap values at two pointers */
    private static void swap(char[] a, int x, int y) {
        char temp;
        temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }

    public static void main(String[] args) {
        String a = "ABCDEFG";
        AllPermutations.permute(a.toCharArray(), 0, a.length() - 1);
    }
}
