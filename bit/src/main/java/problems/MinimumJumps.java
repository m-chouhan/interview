package problems;

import java.util.List;

public class MinimumJumps {

    /**
     * https://leetcode.com/problems/jump-game
     *
     * @param input
     * @return
     */
    public List<Integer> findJumpIndices(List<Integer> input) {

        return null;
    }

    /**
     * @param input      -> allowed jump indices
     * @param startIndex -> index from where we need to count
     * @return minimum jumps starting from start index
     * -1 if invalid or not reachable
     */
    public int findMinJumpCount(List<Integer> input, int startIndex) {
        if (startIndex >= input.size() || startIndex < 0)
            return -1;

        int allowedJumps = input.get(startIndex);
        //treating -vs also as not reachable
        if (allowedJumps <= 0) return -1;
        //reached to the end :)
        if (startIndex == (input.size() - 1))
            return 1;

        //assume not reachable in the begninning
        int minJumps = -1;

        for (int index = 1; index <= allowedJumps; ++index) {
            int jumps = findMinJumpCount(input, startIndex + index);

            if (jumps == -1) continue;
            if (minJumps == -1 || jumps < minJumps) minJumps = jumps;
        }
        //bubble up not reachable case
        return (minJumps == -1) ? minJumps : minJumps + 1;
    }


    public boolean canJump(int[] nums) {
        if (nums == null || nums.length <= 1) return true;
        int[] cache = new int[nums.length];

        for (int i = cache.length - 1; i >= 0; --i) {
            int lastIndex = nums[i] + i;
            if (lastIndex >= (cache.length - 1)) {
                cache[i] = 1;
                continue;
            }
            int j = lastIndex;
            while (j > i) {
                if (cache[j] == 1) break;
                j--;
            }
            cache[i] = (i == j) ? -1 : 1;
        }
        return cache[0] == 1;
    }

    public boolean canJumpOptimized(int[] nums) {
        if (nums == null || nums.length <= 1) return true;

        int i = 0;
        int maxReach = 0;
        int lastIndex = nums.length - 1;

        while (maxReach >= i && i <= lastIndex) {
            if (maxReach >= lastIndex) return true;
            maxReach = Math.max(maxReach, i + nums[i]);
            ++i;
        }
        return false;
    }
}
