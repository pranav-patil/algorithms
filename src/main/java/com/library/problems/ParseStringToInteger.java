package com.library.problems;

public class ParseStringToInteger {

    public static int parseToInteger(String numberString) {

        char charArray[] = numberString.toCharArray();
        int sum = 0;
        //get ascii value for zero
        int zeroAscii = (int) '0';
        for (char c : charArray) {
            int tmpAscii = (int) c;
            sum = (sum * 10) + (tmpAscii - zeroAscii);
        }
        return sum;
    }

    public static void main(String a[]) {
        System.out.println("\"3256\" == " + parseToInteger("3256"));
        System.out.println("\"76289\" == " + parseToInteger("76289"));
        System.out.println("\"90087\" == " + parseToInteger("90087"));
    }
}
