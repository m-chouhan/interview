package problems.dynamic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://practice.geeksforgeeks.org/problems/optimal-strategy-for-a-game/0
 * Find best cost for player A if both plays optimally
 */
public class CoinGameOptimalStrategy {

    public static int [] sumCache;
    public static int [][] cache;

    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        while(T-- > 0) {
            int N = scanner.nextInt(), i = 0;
            if(N == 0) {
                System.out.println("0");
                continue;
            }

            int [] array = new int[N];
            while(i < N)
                array[i++] = scanner.nextInt();

            cache = new int[N][N];
            for(int [] item : cache)
                Arrays.fill(item, -1);

            sumCache = new int[N];
            sumCache[0] = array[0];
            for(i = 1; i < N; ++i)
                sumCache[i] = sumCache[i-1] + array[i];

            int ans = findOptimal(0, N-1, array);
            System.out.println(ans);
        }
    }

    public static int findOptimal(int start, int end,
                                  int [] array) {

        if(start == end) return array[start];
        if(cache[start][end] == -1) {
            int sum = sumCache[end] - sumCache[start] + array[start];
            int caseA = sum - findOptimal(start+1, end, array);
            int caseB = sum - findOptimal(start, end-1, array);
            cache[start][end] = Math.max(caseA, caseB);
        }
        return cache[start][end];
    }

}
