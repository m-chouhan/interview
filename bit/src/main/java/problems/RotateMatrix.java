package problems;

/**
 * GOOGLE
 * https://leetcode.com/problems/rotate-image/
 */
class RotateMatrix {

    public void rotate(int[][] array, int[][] matrix) {
        int temp = matrix[array[3][0]][array[3][1]];
        for (int k = 3; k > 0; --k) {
            matrix[array[k][0]][array[k][1]] =
                    matrix[array[k - 1][0]][array[k - 1][1]];
        }
        matrix[array[0][0]][array[0][1]] = temp;
    }

    public void rotateLayer(int startIndex,
                            int length,
                            int[][] matrix) {

        int i = startIndex, j = startIndex;
        int lastIndex = length - 1;
        for (int k = 0; k < lastIndex; ++k) {
            int array[][] = {
                    {i, j + k},
                    {i + k, j + lastIndex},
                    {i + lastIndex, j + lastIndex - k},
                    {i + lastIndex - k, j}
            };
            rotate(array, matrix);
        }
    }

    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length <= 1)
            return;

        final int N = matrix.length;
        for (int i = 0; i < N / 2; ++i) {
            rotateLayer(i, N - 2 * i, matrix);
        }
    }
}