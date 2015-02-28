package com.library.algorithms.string;

public class LongestPalindrome {

    public static String LongestPalindromeImprove(String in) {
        char[] input = in.toCharArray();
        int longestStart = 0;
        int longestEnd = 0;

        //Scan from mid to both ends
        for (int mid = 0; mid < input.length; mid++) {

            //for odd case, for example 12321 when we choose 3 as mid
            int left = mid;
            int right = mid;

            while (left >= 0 && right < input.length) {

                if (input[left] == input[right]) { //if still palindrome match by one step further each loop cycle

                    //check if the palindrome is longer than previous long palindrome
                    if (right - left > longestEnd - longestStart) {
                        longestStart = left;
                        longestEnd = right;
                    }
                } else {
                    break;
                }

                left--;
                right++;
            }

            // for even case, for example 123321 when we choose 33 as mid
            left = mid;
            right = mid + 1;

            while (left >= 0 && right < input.length) {

                if (input[left] == input[right]) { //if still palindrome match by one step further each loop cycle

                    if (right - left > longestEnd - longestStart) {
                        longestStart = left;
                        longestEnd = right;
                    }
                }
                left--;
                right++;
            }
        }

        return in.substring(longestStart, longestEnd + 1);
    }

    public static String LongestPalindromeNaive(String in) {

        char[] input = in.toCharArray();
        int longestStart = 0;
        int longestEnd = 0;

        // Arbitrarily select start and end
        for (int start = 0; start < input.length; start++) {

            for (int end = start + 1; end <= input.length; end++) { //notice we add one to end because substring in java ends with endindex-1

                if (isPalindrome(input, start, end - 1)) { //in order to use our support method to access char in array, need to adjust the endindex by 1

                    //if it is a longer palindrome we update our global longest palindrome
                    if (end - start > longestEnd - longestStart) {
                        longestEnd = end;
                        longestStart = start;
                    }
                }
            }
        }

        return in.substring(longestStart, longestEnd);
    }

    // scan from 1st to the mid point to see the reverse side char same as the picked one and return
    private static boolean isPalindrome(char[] input, int start, int end) {

        for (int i = start; i <= (start + end) / 2; i++) {
            if (input[i] == input[start + end - i]) {
                continue;
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * Transform S into T.
     * For example, S = "abba", T = "^#a#b#b#a#$".
     * ^ and $ signs are sentinels appended to each end to avoid bounds checking
     */
    private String preProcess(String string) {
        int n = string.length();
        if (n == 0) {
            return "^$";
        }

        String ret = "^";
        for (int i = 0; i < n; i++) {
            ret += "#" + string.substring(i, i + 1);
        }

        ret += "#$";
        return ret;
    }

    private String longestPalindrome(String input) {
        String string = preProcess(input);
        int n = string.length();
        char[] strArray = string.toCharArray();
        int[] P = new int[n];
        int C = 0, R = 0;

        for (int i = 1; i < n - 1; i++) {
            int i_mirror = 2 * C - i; // equals to i' = C - (i-C)

            P[i] = (R > i) ? Math.min(R - i, P[i_mirror]) : 0;

            // Attempt to expand palindrome centered at i
            while (strArray[i + 1 + P[i]] == strArray[i - 1 - P[i]])
                P[i]++;

            // If palindrome centered at i expand past R,
            // adjust center based on expanded palindrome.
            if (i + P[i] > R) {
                C = i;
                R = i + P[i];
            }
        }

        // Find the maximum element in P.
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < n - 1; i++) {
            if (P[i] > maxLen) {
                maxLen = P[i];
                centerIndex = i;
            }
        }
        P = null;

        return input.substring((centerIndex - 1 - maxLen) / 2, maxLen);
    }

    public static void main(String[] args) {
        String in = "cabcbabcbabcba";//expected longest palindrome is "1234321"
        System.out.println("Longest palindrome (naive) " + LongestPalindromeNaive(in));
        System.out.println("Longest palindrome (Improve) " + LongestPalindromeImprove(in));
        LongestPalindrome palindrome = new LongestPalindrome();
        System.out.println(palindrome.longestPalindrome("cabcbabcbabcba"));
    }
}
