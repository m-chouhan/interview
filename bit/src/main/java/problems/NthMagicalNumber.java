package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NthMagicalNumber {

    int findMagicalNumberIn(int [] array) {
        return findMagicalNumberIn(array,0, array.length-1);
    }

    //both are inclusive
    private int findMagicalNumberIn(int []array, int start, int end) {
        if(start == end)
            return array[start] == start ? start : -1;
        int mid = (start + end)/2;
        List<List<Integer>> output = new ArrayList<>();
        int arr[] = new int[] {1,2,3};

        if(array[mid] == mid) return mid;
        else if(array[mid] < mid) {
           return findMagicalNumberIn(array, mid+1, end);
        }
        return findMagicalNumberIn(array, start, mid-1);
    }
}
