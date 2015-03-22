package com.library.algorithms.sorting;

import java.util.Random;

/**
 * Find the kth smallest and largest element in the array.
 *
 * http://www.geekviewpoint.com/java/search/quickselect
 * http://www.algorithmsandme.com/2013/08/find-kth-smallest-element-application.html
 */
public class QuickSelect {

    private static int quickSelectSmallest(int A[], int start, int end, int k) {

        if (start < end) {
            int pivot = partitionSmallest(A, start, end);
            if (pivot == k - 1) {
                return A[pivot];
            }
            if (pivot > k - 1) {
                return quickSelectSmallest(A, start, pivot, k);
            } else {
                return quickSelectSmallest(A, pivot + 1, end, k);
            }
        }
        else {
            if(start == end) {
                return A[start];
            }
            else{
                throw new IllegalStateException("start should always be less than or equal to end.");
            }
        }
    }

    private static int partitionSmallest(int A[], int start, int end) {
        int pivot = start;
        int i = start + 1;
        int j = end;

        while (i < j) {
            while (A[i] < A[pivot] && i < end) {
                i++;
            }
            while (A[j] > A[pivot] && j >= start) {
                j--;
            }
            if (i < j) {
                swap(A, i, j);
            }
        }
        swap(A, j, pivot);
        return pivot;
    }

    private static int quickselectLargest(int[] A, int start, int end, int k) {
        if (start <= end) {
            k = k - 1; // kth largest element is k - 1 th element from 0 to length-1
            int pivot = partitionLargest(A, start, end);
            if (pivot == k) {
                return A[pivot];
            }
            if (pivot > k) {
                return quickselectLargest(A, start, pivot - 1, k);
            }
            return quickselectLargest(A, pivot + 1, end, k);
        }
        return Integer.MIN_VALUE;
    }

    private static int partitionLargest(int[] A, int start, int end) {
        int pivot = start + new Random().nextInt(end - start + 1);
        swap(A, end, pivot);
        for (int i = start; i < end; i++) {
            if (A[i] > A[end]) {
                swap(A, i, start);
                start++;
            }
        }
        swap(A, start, end);
        return start;
    }

    private static void swap(int A[], int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static void main(String[] args) {
        int array[] = {21, 3, 34, 5, 13, 8, 2, 55, 1, 19}; //{4, 2, 1, 7, 5, 3, 8, 10, 9, 6};
        System.out.println("Kth Smallest Element: " + quickSelectSmallest(array, 0, array.length - 1, 4));
        System.out.println("Kth Largest Element: " + quickselectLargest(array, 0, array.length - 1, 4));
    }
}
