package com.library.algorithms.arrays;

public class MaxSubsequenceSum {

    public static long timeCubed(int[] list) {
        return timeCubed(list, list.length);
    }

    public static long timeCubed(int[] list, int len) {
        if (len > list.length || len < 0) len = list.length;
        long start, end;
        start = System.currentTimeMillis();
        int maxSum = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int thisSum = 0;
                for (int k = i; k <= j; k++) thisSum += list[k];
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        //return maxSum;
        end = System.currentTimeMillis();
        System.out.println("Cub sum: " + maxSum);
        return end - start;
    }

    public static long timeSquared(int[] list) {
        return timeSquared(list, list.length);
    }

    public static long timeSquared(int[] list, int len) {

        if (len > list.length || len < 0) len = list.length;
        long start, end;
        start = System.currentTimeMillis();
        int maxSum = 0;
        for (int i = 0; i < len; i++) {
            int thisSum = 0;
            for (int j = i; j < len; j++) {
                thisSum += list[j];
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        //return maxSum;
        end = System.currentTimeMillis();
        System.out.println("Squ sum: " + maxSum);
        return end - start;
    }

    public static long timeLinear(int[] list) {
        return timeLinear(list, list.length);
    }

    public static long timeLinear(int[] list, int len) {

        if (len > list.length || len < 0) len = list.length;
        long start, end;
        start = System.currentTimeMillis();
        int maxSum = 0, thisSum = 0;
        for (int i = 0; i < len; i++) {
            thisSum += list[i];
            if (thisSum > maxSum) {
                maxSum = thisSum;
            } else if (thisSum < 0) thisSum = 0;
        }
        //return maxSum;
        end = System.currentTimeMillis();
        System.out.println("Lin sum: " + maxSum);
        return end - start;
    }

    public static long timeLogarithmic(int[] list) {
        return timeLogarithmic(list, list.length);
    }

    public static long timeLogarithmic(int[] list, int len) {
        if (len > list.length || len < 0) len = list.length;
        long start, end;
        start = System.currentTimeMillis();
        int sum = timeLog(list, 0, len - 1);
        end = System.currentTimeMillis();
        System.out.println("Log sum: " + sum);
        return end - start;
    }

    public static void steps() {
        for (int i = 20; i <= 800; i += 20) {
            System.out.println(calc(i));
        }
    }

    private static int calc(int n) {
        if (n <= 1) return 2;
        return 16 + 4 * n + 2 * calc(n / 2);
    }

    private static int timeLog(int[] list, int left, int right) {
        if (left >= right) return (list[left] > 0 ? list[left] : 0);
        int center = (left + right) / 2;
        int maxLeftSum = timeLog(list, left, center);
        int maxRightSum = timeLog(list, center + 1, right);
        int maxLeftBorderSum = 0, leftBorderSum = 0;
        for (int i = center; i >= left; i--) {
            leftBorderSum += list[i];
            if (leftBorderSum > maxLeftBorderSum) maxLeftBorderSum = leftBorderSum;
        }
        int maxRightBorderSum = 0, rightBorderSum = 0;
        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += list[i];
            if (rightBorderSum > maxRightBorderSum) maxRightBorderSum = rightBorderSum;
        }
        return Math.max(leftBorderSum, Math.max(rightBorderSum, maxLeftBorderSum + maxRightBorderSum));
    }

    public static void main(String[] args) {
        //Create the list of random integers in the interval [-100,100]
        int[] list = new int[9000000];//8000];//{3,4,(-7),1,9,(-3),2};//{3,4,(7),1,9,(-3),2};
        java.util.Random r = new java.util.Random();
        for (int i = 0; i < list.length; i++) {
            list[i] = r.nextInt(200) - 100;
        }

        /*
        //Print out the list, 100 integers per line 
        System.out.print("{");
        for(int i = 1; i < list.length - 1; i++) {
            if(i % 100 == 0) System.out.println();
            System.out.print(list[i] + ", ");
        }
        System.out.println(list[list.length - 1] + "}");
        */
       
        /*
        //Print out the execution time (the time functions print the result) 
        long cubed = timeCubed(list);
        System.out.println("Cub time: " + cubed);
        long squared = timeSquared(list);
        System.out.println("Squ time: " + squared);
        */
        long log_t = timeLogarithmic(list);
        System.out.println("Log time: " + log_t);
        long lin_t = timeLinear(list);
        System.out.println("Lin time: " + lin_t);
    }

    public static void cubedAttempt() {
        final int bignum = 5000;
        int[] list = new int[bignum];
        java.util.Random r = new java.util.Random();
        for (int n = 1; n <= 20; n++) {
            for (int i = 0; i < n * bignum / 20; i++) {
                list[i] = r.nextInt(200) - 100;
            }
            System.out.println(timeCubed(list, n * bignum / 20));
        }
        System.out.println("Complete");
    }

    public static void squaredAttempt() {
        final int bignum = 40000;
        int[] list = new int[bignum];
        java.util.Random r = new java.util.Random();
        for (int n = 1; n <= 20; n++) {
            for (int i = 0; i < n * bignum / 20; i++) {
                list[i] = r.nextInt(200) - 100;
            }
            System.out.println(timeSquared(list, n * bignum / 20));
        }
        System.out.println("Complete");
    }

    public static void logarithmicAttempt() {
        final int bignum = 9000000;
        int[] list = new int[bignum];
        java.util.Random r = new java.util.Random();
        for (int n = 1; n <= 20; n++) {
            for (int i = 0; i < n * bignum / 20; i++) {
                list[i] = r.nextInt(200) - 100;
            }
            System.out.println(timeLogarithmic(list, n * bignum / 20));
        }
        System.out.println("Complete");
    }

    public static void linearAttempt() {
        final int bignum = 9000000;
        int[] list = new int[bignum];
        java.util.Random r = new java.util.Random();
        for (int n = 1; n <= 20; n++) {
            for (int i = 0; i < n * bignum / 20; i++) {
                list[i] = r.nextInt(200) - 100;
            }
            System.out.println(timeLinear(list, n * bignum / 20));
        }
        System.out.println("Complete");
    }
}
