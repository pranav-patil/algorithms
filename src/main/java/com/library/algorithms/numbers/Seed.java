package com.library.algorithms.numbers;

/**
 * Find the seed of a number.
 * Eg : 1716 = 143*1*4*3 =1716 so 143 is the seed of 1716.
 * find all possible seed for a given number.
 */
public class Seed {

    public static int findSeed(int number) {

        int product = 1;
        int seed = 1;

        while (seed < (number * 0.5)){
            if (number % seed == 0){
                int factor = seed;
                product = factor;
                while(factor != 0){
                    int mod = factor % 10;
                    factor = factor / 10;
                    product *= mod;
                }
                if (product == number){
                    return seed;
                }
            }
            seed++;
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findSeed(1716));
    }
}
