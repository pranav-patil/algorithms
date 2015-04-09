package com.library.algorithms.numbers;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumber {

    /**
     * Second version of isPrimeNumber method, with improvement like not
     * checking for division by even number, if its not divisible by 2.
     *
     * http://www.mkyong.com/java/how-to-determine-a-prime-number-in-java/
     *
     * @param number
     * @return
     */
    public static boolean isPrimeNumber(int number) {
        if (number == 2 || number == 3) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        int sqrt = (int) Math.sqrt(number) + 1;
        for (int i = 3; i < sqrt; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get all the prime numbers under the number, using the seive approach.
     * http://codereview.stackexchange.com/questions/62361/find-prime-numbers-under-n
     *
     * @param number
     * @return
     */
    public List<Integer> getPrimes(final int number) {
        final boolean[] nonPrime = new boolean[number + 1];

        for (int i = 2; i <= Math.sqrt(number); ++i) {
            if (!nonPrime[i]) {
                for (int j = i * 2; j <= number; j += i) {
                    nonPrime[j] = true;
                }
            }
        }

        final List<Integer> primes = new ArrayList<>();

        for (int i = 2; i <= number; ++i) {
            if (!nonPrime[i]) primes.add(i);
        }

        return primes;
    }
}
