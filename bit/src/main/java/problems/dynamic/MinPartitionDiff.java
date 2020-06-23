package problems.dynamic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Given an array partition into 2 subsets
 * such that diff of sum is minimized
 * https://practice.geeksforgeeks.org/problems/minimum-sum-partition/0
 */
public class MinPartitionDiff {

    static int [][] cache;
    static int midValue;

    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        while(T-- > 0) {
            int N = scanner.nextInt(), i = 0;
            int []array = new int[N];
            while(i < N)
                array[i++] = scanner.nextInt();

            midValue = Arrays.stream(array).sum();

            cache = new int[N][midValue*2];
            for(int[] item : cache)
                Arrays.fill(item, -1);

            int min = minPartitionOf(N-1, 0, array);
            System.out.println(min);
        }
    }

    public static int minPartitionOf(int index, int diff, int []array) {
        if(index == 0)
            return Math.min(
                    Math.abs(diff - array[index]),
                    Math.abs(diff + array[index])
            );

        int diffIndex = midValue+diff;
        if(cache[index][diffIndex] == -1)
            cache[index][diffIndex] = Math.min(
                    minPartitionOf(index-1, diff - array[index], array),
                    minPartitionOf(index-1, diff + array[index], array)
            );
        return cache[index][diffIndex];
    }
}
