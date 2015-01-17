package com.library.algorithms.string;

import java.util.HashSet;
import java.util.Set;

public class CommonSubsequence {

    public static String lowestCommonSubsequence(String string1, String string2) {

        char[] source = string1.toCharArray();
        char[] target = string2.toCharArray();
        int[][] L = new int[source.length + 1][target.length + 1];
        Set<String> lcsSet = new HashSet<String>();
        int longest = 0;

        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < target.length; j++) {
                if (source[i] == target[j]) {
                    int v = L[i][j] + 1;
                    L[i + 1][j + 1] = v;

                    if (v > longest) {
                        longest = v;
                        lcsSet = new HashSet<String>();
                    }
                    if (v == longest) {
                        lcsSet.add(string1.substring(i - v + 1, i + 1));
                    }
                }
            }
        }

        return lcsSet.toString();
    }

    public static void main(String[] args) {
        System.out.println("Length of LCS is " + lowestCommonSubsequence("GDRGANGSOFNEWYORKGHTONYSTARKDF", "GXTONYSTARKXAGANGSOFNEWYORKYB"));
        System.out.println("Length of LCS is " + lowestCommonSubsequence("FGHJ", "ASFFASDFHASDF"));
    }
}
