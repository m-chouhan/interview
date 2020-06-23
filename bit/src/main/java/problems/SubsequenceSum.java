package problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * calculate total subarrays with given sum
 */
public class SubsequenceSum {

    static int calculateSumCount(List<Integer> list, int target) {
        if (list == null || list.size() == 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int subarrayCount = 0;
        int currentSum = 0;
        for (int i = 0; i < list.size(); ++i) {
            currentSum += list.get(i);
            int currentSumCount = map.containsKey(currentSum) ? map.get(currentSum) + 1 : 1;
            map.put(currentSum, currentSumCount);
            if (map.containsKey(currentSum - target)) {
                subarrayCount += map.get(currentSum - target);
            }
        }

        return subarrayCount;
    }

    public static void main(String args[]) {

        System.out.println(calculateSumCount(Arrays.asList(1, 2, 0, 10, 10, 0), 10));
    }
}
