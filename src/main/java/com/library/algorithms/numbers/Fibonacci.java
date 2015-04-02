package com.library.algorithms.numbers;


public class Fibonacci {

    public static int recursive(int number) {
        if (number <= 1) {
            return number;
        } else {
            return recursive(number - 1) + recursive(number - 2);
        }
    }

    public static int nonrecursive(int number) {

        if (number <= 1) {
            return number;
        }

        int fibo = 1;
        int fiboPrev = 1;

        for (int i = 2; i < number; ++i) {
            int temp = fibo;
            fibo += fiboPrev;
            fiboPrev = temp;
        }
        return fibo;
    }

    public static void main(String[] args) {
        System.out.print("Recursive fibonacci series: ");
        for (int i=0; i < 10; i++) {
            System.out.print(recursive(i) + ", ");
        }
        System.out.println();

        System.out.print("Non-Recursive fibonacci series: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(nonrecursive(i) + ", ");
        }
        System.out.println();

    }
}
