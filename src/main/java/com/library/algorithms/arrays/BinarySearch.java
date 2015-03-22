package com.library.algorithms.arrays;

/**
 * http://www.cs.utsa.edu/~wagner/CS3343/recursion/binsearch.html
 */
public class BinarySearch {

    public static int binarySearch(int[] sortedArray, int key) {
        int start = 0;
        int end = sortedArray.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (sortedArray[mid] == key) {
                return mid;
            } else if (sortedArray[mid] < key) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] sortedArray, int start, int end, int key) {
        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;

        if (sortedArray[mid] == key) {
            return mid;
        } else if (sortedArray[mid] < key) {
            return binarySearch(sortedArray, mid + 1, end, key);
        } else {
            return binarySearch(sortedArray, start, mid - 1, key);
        }
    }

    public static void main(String[] args) {
        int[] array = {2, 45, 234, 567, 876, 900, 976, 999};
        int index = binarySearch(array, 0, array.length, 45);
        System.out.println("Found 45 at " + index + " index");
        index = binarySearch(array, 0, array.length, 999);
        System.out.println("Found 999 at " + index + " index");
        index = binarySearch(array, 0, array.length, 876);
        System.out.println("Found 876 at " + index + " index");
    }
}
