package problems;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/description/
 */
public class RotatedArraySearch {

    private int offset = 0;
    public int[] input;

    public int search(int[] nums, int target) {

        if (nums.length == 0) return -1;

        offset = findStartIndex(nums, 0, nums.length - 1);
        input = nums;
        int output = binarySearch(target, 0, nums.length - 1);
        return (output == -1) ? output :
                (output + offset) % nums.length;
    }

    public int binarySearch(int target, int startIndex, int endIndex) {

        if (startIndex == endIndex)
            return get(startIndex) == target ? startIndex : -1;
        if (startIndex == endIndex - 1) {
            if (get(startIndex) == target) return startIndex;
            if (get(endIndex) == target) return endIndex;
            return -1;
        }

        int mid = (startIndex + endIndex) / 2;
        if (get(mid) == target) return target;
        if (get(mid) < target)
            return binarySearch(target, mid + 1, endIndex);
        return binarySearch(target, startIndex, mid - 1);
    }

    public int get(int index) {
        return input[(index + offset) % input.length];
    }

    public int findStartIndex(int[] array, int startIndex, int endIndex) {
        if (isWellFormed(array, startIndex, endIndex)) {
            return startIndex;
        }
        if (startIndex == endIndex - 1)
            return endIndex;

        int mid = (startIndex + endIndex) / 2;

        if (isWellFormed(array, startIndex, mid)) return findStartIndex(array, mid, endIndex);
        return findStartIndex(array, startIndex, mid);
    }

    private boolean isWellFormed(int[] array, int startIndex, int endIndex) {
        return startIndex == endIndex || array[startIndex] < array[endIndex];
    }

}
