package com.library.algorithms.arrays;

import com.library.algorithms.sorting.Sorting;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * http://javarevisited.blogspot.com/2014/08/how-to-find-all-pairs-in-array-of-integers-whose-sum-equal-given-number-java.html
 */
public class PairsWIthSum {

    /**
     * Complexity: O(n^2)
     *
     * @param array
     * @param sum
     */
    public static void findPairsOfSumUsingBruteforce(int[] array, int sum) {
        for (int i = 0; i < array.length; i++) {
            int first = array[i];
            for (int j = i + 1; j < array.length; j++) {
                int second = array[j];
                if ((first + second) == sum) {
                    System.out.printf("(%d, %d) %n", first, second);
                }
            }
        }
    }

    /**
     * If the numbers are not negative numbers. Complexity is O(N).
     *
     * @param array
     * @param sum
     */
    public static void findPairsOfSumUsingHash(int[] array, int sum) {
        if (array.length < 2) {
            return;
        }

        Set set = new HashSet(array.length);

        for (int value : array) {
            int target = sum - value;
            // if target number is not in set then add
            if (!set.contains(target)) {
                set.add(value);
            } else {
                System.out.printf("(%d, %d) %n", value, target);
            }
        }
    }

    /**
     * Uses two pointers to scan through array from both direction i.e.
     * beginning and end. If sum of both the values are equal to given number then we
     * output the pair and advance them. If the sum of two numbers is less than k then
     * we increase the left pointer, else if the sum is greater than k we decrement the
     * right pointer, until both pointers meet at some part of the array.
     * <p/>
     * The complexity of this solution would be O(NlogN) due to sorting
     *
     * @param array
     * @param sum
     */
    public static void findPairsOfSumUsingTwoPointers(int[] array, int sum) {
        if (array.length < 2) {
            return;
        }

        Arrays.sort(array);
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            int currentSum = array[left] + array[right];
            if (currentSum == sum) {
                System.out.printf("(%d, %d) %n", array[left], array[right]);
                left = left + 1;
                right = right - 1;
            } else if (currentSum < sum) {
                left = left + 1;
            } else if (currentSum > sum) {
                right = right - 1;
            }
        }
    }

    public static void findPairsOfSumUsingBinarySearch(int[] array, int sum) {
        Sorting.quickSort(array, 0, array.length-1);

        for (int i = 0; i < array.length; i++) {
            if (BinarySearch.binarySearch(array, sum - array[i]) != -1) {
                System.out.printf("(%d, %d) %n", array[i], sum - array[i]);
            }
        }
    }

    public static void main(String args[]) {
        int[] array1 = new int[] {12, 14, 17, 15, 19, 20, -11 };
        findPairsOfSumUsingBinarySearch(array1, 9);
        findPairsOfSumUsingTwoPointers(array1, 9);
        int[] array2 = new int[] { 2, 4, 7, 5, 9, 10, -1 };
        System.out.println("---------------------------------------------------------");
        findPairsOfSumUsingBinarySearch(array2, 9);
        findPairsOfSumUsingTwoPointers(array2, 9);
    }
}
