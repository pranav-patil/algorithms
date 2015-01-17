package com.library.algorithms.string;

public class LevenshteinDistance {

    public static int levenshteinDistance(String str1, String str2) {

        char[] chr1 = str1.toCharArray();
        char[] chr2 = str2.toCharArray();
        int[][] distance = new int[chr1.length + 1][chr2.length + 1];

        for (int y = 0; y <= chr1.length; y++) {
            distance[y][0] = y;
        }

        for (int x = 0; x <= chr2.length; x++) {
            distance[0][x] = x;
        }

        int cost = 0;
        for (int i = 0; i < chr1.length; i++) {
            for (int j = 0; j < chr2.length; j++) {
                cost = 1;
                if (chr1[i] == chr2[j]) {
                    cost = 0;
                }

                distance[(i + 1)][(j + 1)] = minimum(distance[i][(j + 1)] + 1,
                                                     distance[(i + 1)][j] + 1,
                                                     distance[i][j] + cost );
            }
        }

        return distance[chr1.length][chr2.length];
    }

    /**
     * http://www.gettingcirrius.com/2011/06/calculating-similarity-part-3-damerau.html
     * @param source
     * @param target
     */
    public static void damerauLevenshteinDistance(String source, String target)
    {
        assert ((!source.isEmpty()) || (!target.isEmpty()));

        int[][] distanceMatrix = new int[source.length() + 1][target.length() + 1];
        for (int sourceIndex = 0; sourceIndex <= source.length(); sourceIndex++) {
            distanceMatrix[sourceIndex][0] = sourceIndex;
        }
        for (int targetIndex = 0; targetIndex <= target.length(); targetIndex++) {
            distanceMatrix[0][targetIndex] = targetIndex;
        }

        int cost = 0;

        for (int sourceIndex = 1; sourceIndex <= source.length(); sourceIndex++) {
            for (int targetIndex = 1; targetIndex <= target.length(); targetIndex++) {

                if (source.charAt(sourceIndex - 1) == target.charAt(targetIndex - 1)) {
                    cost = 0;
                } else {
                    cost = 1;
                }

                distanceMatrix[sourceIndex][targetIndex] =
                        minimum(
                                distanceMatrix[sourceIndex - 1][targetIndex] + 1,
                                distanceMatrix[sourceIndex][targetIndex - 1] + 1,
                                distanceMatrix[sourceIndex - 1][targetIndex - 1] + cost);

                if(sourceIndex == 1 || targetIndex == 1){
                    continue;
                }

                //transposition check (if the current and previous
                //character are switched around (e.g.: t[se]t and t[es]t)...
                if(source.charAt(sourceIndex - 1) == target.charAt(targetIndex - 2) &&
                   source.charAt(sourceIndex - 2) == target.charAt(targetIndex - 1)){

                        distanceMatrix[sourceIndex][targetIndex] = minimum(
                                //Current cost
                                distanceMatrix[sourceIndex][targetIndex],
                                //Transposition
                                distanceMatrix[sourceIndex - 2][targetIndex - 2] + cost);
                }
            }
        }
        System.out.println("DISTANCE: " + distanceMatrix[(distanceMatrix.length - 1)][(distanceMatrix[0].length - 1)]);
    }

    private static int minimum(int... values) {
        int min = Integer.MAX_VALUE;
        for (int value : values) {
            min = Math.min(value, min);
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println("DISTANCE: " + levenshteinDistance("kitten", "sitting"));
        damerauLevenshteinDistance("kitten", "sitting");
    }
}
