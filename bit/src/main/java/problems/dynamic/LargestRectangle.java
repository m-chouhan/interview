package problems.dynamic;

import java.util.Arrays;

/**
 * GOOGLE
 * https://leetcode.com/problems/maximal-rectangle/
 * find largest rectangle of ones in array
 */
class LargestRectangle {

    int[][] countMatrix;

    void preProcess(char[][] matrix) {

        countMatrix = new int[matrix.length][matrix[0].length];
        int count = 0;
        for (int i = 0; i < matrix.length; ++i)
            for (int j = 0; j < matrix[0].length; ++j) {

                if (i > 0) countMatrix[i][j] += countMatrix[i - 1][j];
                if (j > 0) countMatrix[i][j] += countMatrix[i][j - 1];
                if (i > 0 && j > 0)
                    countMatrix[i][j] -= countMatrix[i - 1][j - 1];
                if (matrix[i][j] == '1')
                    countMatrix[i][j]++;
            }
    }

    public boolean isValid(int i1, int j1, int i2, int j2, char[][] matrix) {

        int noOfOnes = countMatrix[i2][j2];
        if (i1 > 0) noOfOnes -= countMatrix[i1 - 1][j2];
        if (j1 > 0) noOfOnes -= countMatrix[i2][j1 - 1];
        if (i1 > 0 && j1 > 0) noOfOnes += countMatrix[i1 - 1][j1 - 1];

        return noOfOnes == (i2 - i1 + 1) * (j2 - j1 + 1);
    }

    public int maximalRectangle(char[][] matrix) {
        //base cases
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        if (matrix.length == 1 && matrix[0].length == 1)
            return matrix[0][0] == '0' ? 0 : 1;

        preProcess(matrix);

        int area = 0;
        for (int i1 = 0; i1 < matrix.length; ++i1)
            for (int j1 = 0; j1 < matrix[0].length; ++j1)
                for (int i2 = i1; i2 < matrix.length; i2++)
                    for (int j2 = j1; j2 < matrix[0].length; ++j2) {
                        area = isValid(i1, j1, i2, j2, matrix)
                                ? Math.max(area, (i2 - i1 + 1) * (j2 - j1 + 1))
                                : area;
                    }

        return area;
    }


    /**
     * Dynamic Programming Solution (FAILED ATTEMPT)
     * REASON :
     * Did not think about edge cases, was thinking in terms of find squares problem,
     * but rectangles can be of different bizzare lengths,
     * there can be many more variations of rectangles possible for i-1,j-1 which are candidates for i,j
     */

    public static class Data {
        public int iLength, jLength,
                rectI, rectJ;
        public final int value;

        public Data(int value) {
            this.value = value;
        }
    }

    Data[][] cache;

    void preProcessDP(char[][] matrix) {
        cache = new Data[matrix.length][matrix[0].length];
        int array[] = new int[10];
        Arrays.stream(array).max().getAsInt();
        for (int i = 0; i < matrix.length; ++i)
            for (int j = 0; j < matrix[0].length; ++j) {

                cache[i][j] = new Data(matrix[i][j] == '0' ? 0 : 1);
                if (cache[i][j].value == 0) continue;

                cache[i][j].iLength = (i > 0) ? cache[i - 1][j].iLength + 1 : 1;
                cache[i][j].jLength = (j > 0) ? cache[i][j - 1].jLength + 1 : 1;

                cache[i][j].rectI = (i > 0 && j > 0)
                        ? Math.min(cache[i - 1][j - 1].rectI, cache[i - 1][j].iLength) + 1
                        : 1;

                cache[i][j].rectJ = (i > 0 && j > 0)
                        ? Math.min(cache[i - 1][j - 1].rectJ, cache[i][j - 1].jLength) + 1
                        : 1;
            }
    }

    public int maximalRectangleDP(char[][] matrix) {
        //base cases
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        if (matrix.length == 1 && matrix[0].length == 1)
            return matrix[0][0] == '0' ? 0 : 1;

        preProcessDP(matrix);

        int area = 0;
        for (int i = 0; i < matrix.length; ++i)
            for (int j = 0; j < matrix[0].length; ++j) {
                int area2D = cache[i][j].rectI * cache[i][j].rectJ;
                int areaI = cache[i][j].iLength;
                int areaJ = cache[i][j].jLength;
                int currentMax = Math.max(area2D, Math.max(areaI, areaJ));
                area = Math.max(area, currentMax);
            }

        return area;
    }

    public static void main(String[] args) {

        LargestRectangle largestRectangle = new LargestRectangle();
        char input[][] = {{'1', '1', '1', '1'}, {'1', '1', '1', '1'}, {'1', '1', '1', '1'}};
        largestRectangle.maximalRectangleDP(input);
    }
}