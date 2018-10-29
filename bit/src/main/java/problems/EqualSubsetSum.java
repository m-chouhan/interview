package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class EqualSubsetSum {

    public boolean canPartition(int[] nums) {
        if (nums == null) return false;
        if (nums.length == 1) return nums[0] == 0;

        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;

        boolean[][] cache = new boolean[sum + 1][nums.length + 1];
        for (int s = 0; s <= sum / 2; ++s) {
            for (int n = 0; n <= nums.length; ++n) {
                if (s == 0) {
                    cache[s][n] = true;
                    continue;
                } else if (n == 0) {
                    cache[s][n] = false;
                    continue;
                }
                cache[s][n] = cache[s][n - 1] ||
                        (nums[n - 1] <= s && cache[s - nums[n - 1]][n - 1]);
            }
        }
        return cache[sum / 2][nums.length];
    }

    public boolean canPartition2(int[] nums) {
        if (nums == null) return false;
        if (nums.length == 1) return nums[0] == 0;

        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;
        sum = sum / 2;
        boolean cache[] = new boolean[sum + 1];
        cache[0] = true;
        for (int num : nums) {
            for (int s = sum; s >= num; s--)
                cache[s] = cache[s] || cache[s - num];
        }
        return cache[sum];
    }

    public static void main(String args[]) {
        EqualSubsetSum equalSubsetSum = new EqualSubsetSum();
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        for (int val : stack) System.out.println(val);
        Arrays.asList(1);
        System.out.println(equalSubsetSum.canPartition(new int[]{1, 2, 3}));
    }
}
