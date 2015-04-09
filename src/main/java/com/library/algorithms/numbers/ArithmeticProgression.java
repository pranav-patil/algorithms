package com.library.algorithms.numbers;

/**
 * The arithmetic progression might be described by a formula [a_nth = a_1st + (n - 1) r] ,
 * that means that n-th element of it, is a sum of the first element and (n – 1) steps r.
 * We are expected to find the missing number in the array taking an assumption that the
 * first and last element are never missing. Complexity is O(log N).
 * <p/>
 * https://solvethat.wordpress.com/2013/10/27/find-a-missing-number-of-an-arithmetic-progression/
 */
public class ArithmeticProgression {

    /**
     * Assumption that first and last element are never missing.
     */
    public static int findMissingElementWithOn(int[] array) {
        int N = array.length;
        int diff = (array[N - 1] - array[0]) / N;

        //Considering 0 based indexing
        for (int i = 0; i < (N - 2); i++) {
            if (array[i + 1] != (array[i] + diff)) {
                return (array[i] + diff);
            }
        }

        return -1;
    }

    /**
     * http://www.careercup.com/question?id=4798365246160896
     */
    public static int findMissingWithOlogn(int[] array) {
        assert array != null && array.length > 2;

        int diff = Math.min(array[2] - array[1], array[1] - array[0]);

        int low = 0, high = array.length - 1;
        while (low < high) {
            //int mid = (low + high) >>> 1;
            int mid = (low + high) / 2;

            int leftDiff = array[mid] - array[low];
            if (leftDiff > diff * (mid - low)) {
                if (mid - low == 1)
                    return (array[mid] + array[low]) / 2;

                high = mid;
                continue;
            }

            int rightDiff = array[high] - array[mid];
            if (rightDiff > diff * (high - mid)) {
                if (high - mid == 1)
                    return (array[high] + array[mid]) / 2;

                low = mid;
                continue;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] array = {1, 3, 5, 9, 11};
        System.out.println("Missing Number: " + findMissingWithOlogn(array));
        array = new int[]{11, 31, 41, 51};
        System.out.println("Missing Number: " + findMissingWithOlogn(array));
        array = new int[]{1, 3, 7, 9, 11, 13};
        System.out.println("Missing Number: " + findMissingWithOlogn(array));
    }
}
