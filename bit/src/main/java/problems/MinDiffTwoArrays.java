package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MinDiffTwoArrays {

    public int minDiff(int [] A, int [] B) {
        if(A == null || B == null) return -1;
        if(A.length == 0 || B.length == 0) return -1;
        return findMinB(A, B);
    }

    private int findMinA(int []A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        int aIndex = 0, bIndex = 0;
        int min = Integer.MAX_VALUE;

        while(aIndex < A.length && bIndex < B.length && min > 0) {
            if(A[aIndex] < B[bIndex]) {
                min = Math.min(min, B[bIndex] - A[aIndex]);
                aIndex++;
            }
            else {
                min = Math.min(min, A[aIndex] - B[bIndex]);
                bIndex++;
            }
        }

        return min;
    }

    private int findMinB(int []A, int []B) {
        int [] small = (A.length < B.length) ? A : B;
        int [] large = (A.length < B.length) ? B : A;
        int min = Integer.MAX_VALUE;

        Arrays.sort(small);
        for(int item : large) {
            int index = findClosest(item, small);
            if(index == -1) index = small.length-1;
            min = Math.min(min, Math.abs(item - small[index]));
            if(index > 0) min = Math.min(min, Math.abs(item - small[index-1]));
        }
        return min;
    }

    /**
     * @param value : value to search
     * @param array : in this array
     * @return : index of next largest or equal item/ else return -1
     */
    public int findClosest(int value, int []array) {
        int low = 0, high = array.length-1;
        while(low < high) {
            int mid = (low + high)/2;
            if(array[mid] == value) return mid;
            if(array[mid] < value) low = mid+1;
            else if(mid > 0 && array[mid-1] < value) return mid;
            else high = mid-1;
        }
        return low < array.length ? low : -1;
    }
}
