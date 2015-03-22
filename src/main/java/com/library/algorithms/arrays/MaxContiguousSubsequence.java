package com.library.algorithms.arrays;

/**
 * http://karmaandcoding.blogspot.com/2012/02/dynamic-programming-maximum-value.html
 */
public class MaxContiguousSubsequence {

    public static void maxContiguousSum(int[] array) {

        int newStart = 0;
        int start = 0, end = 0;

        // initialize running sum to first element of array
        int runningSum = array[0];
        int maxSum = 0;

        for (int i = 1; i < array.length; i++) {
            if (runningSum > 0) {
                runningSum += array[i];
            } else {
                runningSum = array[i];
                newStart = i;
            }
            if (runningSum > maxSum) {
                maxSum = runningSum;
                start = newStart;
                end = i;
            }
        }
        System.out.println("Max Sum: " + maxSum);
        System.out.println("Indices: i=" + start + ": j=" + end);
    }

    public static void main(String[] args) {
        int[] A = {-2, 11, -4, 13, -5, 2};
        int[] B = {-15, 29, -36, 3, -22, 11, 19, -5};
        maxContiguousSum(A);
        maxContiguousSum(B);
    }
}
