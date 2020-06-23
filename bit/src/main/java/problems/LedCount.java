
package problems;

import java.util.Scanner;

// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

class LedCount {

    static long counter;

    static void generateCombination(long ledCount, long L, boolean[] marked, int[] cost) {
        if (ledCount < 2 || L == 0) return;
        for (int i = 0; i < 10; ++i) {
            if (!marked[i] && cost[i] <= ledCount) {
                marked[i] = true;
                counter++;
                generateCombination(ledCount - cost[i], L - 1, marked, cost);
                marked[i] = false;
            }
        }
    }

    public static void main(String args[]) {

        int[] cost = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};

        Scanner s = new Scanner(System.in);
        long T = s.nextLong();
        while (T-- > 0) {
            counter = 0;
            long M, L;
            boolean[] marked = {false, false, false, false, false, false, false, false, false, false};

            L = s.nextLong();
            M = s.nextLong();

            long ledCount = M / 7;
            generateCombination(ledCount, L, marked, cost);
            System.out.println(counter);
        }
    }
}
