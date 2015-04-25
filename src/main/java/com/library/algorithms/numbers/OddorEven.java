package com.library.algorithms.numbers;

public class OddorEven {

    /**
     * checking if odd number using modulus operator.
     */
    public static boolean isOddByModulus(int i) {
        return i % 2 != 0;
    }

    /** checking even and odd number without using modulus or remainder operator.
     *  Division operator is used to find whether number is an odd number.
     *
     *  Note: Division and modulus are more than twice as expensive as multiplication (a weight 10).
     */

    public static boolean isOddWithoutModulus(int number){
        int quotient = number/2;
        if(quotient*2== number) {
            return false;  // even number
        }

        return true; // odd number
    }

    /** Uses bitwise AND (&) operator to check if a number is an odd number.
     */
    public static boolean isOddByBitwise(int number){
        if((number & 1) == 0) {
            return false;   // even number
        }

        return true; // odd number
    }

    public static void main(String args[]) {
        System.out.println("Checking if a number is even or odd using modulus, division and bitwise operator");
        for(int i= -1; i<2; i++) {
            System.out.println("Using Modulus Operator: "   + isOddByModulus(i));
            System.out.println("Without Modulus Operator: " + isOddWithoutModulus(i));
            System.out.println("Using Bitwise Operator: "   + isOddByBitwise(i));
            System.out.println("-------------------------------------------------------------");
        }
    }
}
