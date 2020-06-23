package problems;

import java.util.Arrays;

public class ReversePairs {
    public int reversePairs(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        return sortNCount(nums, 0, nums.length-1);
    }

    int sortNCount(int [] array, int start, int end) {

        if(start == end) return 0;
        int mid = (start + end)/2;
        int lcount = sortNCount(array, start, mid);
        int rcount = sortNCount(array, mid+1, end);
        int mcount = merge(array, start, end);
        return lcount + rcount + mcount;
    }

    int merge(int [] array, int start, int end) {

        int mid = (start+end)/2;
        int left = start, right = mid+1;
        int count = 0;
        while(left <= mid && right <= end) {

            if(array[left] <= (long)2 * array[right])
                left++;
            else {
                count += mid - left + 1;
                right++;
            }
        }
        Arrays.sort(array, start, end+1);
        return count;
    }

}
