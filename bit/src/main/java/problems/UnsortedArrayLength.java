package problems;

/**
 * https://practice.geeksforgeeks.org/problems/length-unsorted-subarray/0
 * Find min length (start,end) such that sorting the subarray will sort the array
 */

import java.util.*;
import java.lang.*;

public class UnsortedArrayLength {
    static class Pair {
        int min; int max;
        Pair(int min, int max) { this.min = min; this.max = max; }
    }

    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        while(T-- > 0) {
            int N = scanner.nextInt();
            int [] array = new int [N];
            int i = 0;
            while(i < N)
                array[i++] = scanner.nextInt();
            Pair pair = findPair(array);
            System.out.println(pair.min + " " + pair.max);
        }
    }

    static Pair findPair(int [] array) {

        int left_end = findLongestIncreasing(array);
        //already sorted !!
        if(left_end == array.length-1) return new Pair(0, 0);
        int right_start = findLongestDecreasing(array);

        Pair pair = findMinMax(left_end, right_start, array);
        left_end--;right_start++;

        while((left_end >= 0 && pair.min < array[left_end]) || (right_start < array.length && pair.max > array[right_start])) {

            if(left_end >= 0 && pair.min < array[left_end]) {
                pair.max = Math.max(pair.max, array[left_end]);
                left_end--;
            }

            if(right_start < array.length && pair.max > array[right_start]) {
                pair.min = Math.min(pair.min, array[right_start]);
                right_start++;
            }
        }

        return new Pair(left_end+1, right_start-1);
    }

    static int findLongestIncreasing(int[] array) {
        for(int i = 1; i < array.length; ++i)
            if(array[i] < array[i-1]) return i-1;
        return array.length-1;
    }

    static int findLongestDecreasing(int[] array) {
        for(int i = array.length-2; i >= 0; --i)
            if(array[i] > array[i+1]) return i+1;
        return 0;
    }

    static Pair findMinMax(int start, int end, int[] array) {
        Pair pair = new Pair(array[start], array[start]);
        for(int i = start+1; i <= end; ++i) {
            pair.min = Math.min(pair.min, array[i]);
            pair.max = Math.max(pair.max, array[i]);
        }
        return pair;
    }
}
