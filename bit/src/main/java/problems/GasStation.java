package problems;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * GOOGLE/THOUGHSPOT
 * https://leetcode.com/problems/gas-station/
 */
class GasStation {

    //O(N) complexity
    public int canCompleteCircuit(int[] gas, int[] cost) {

        if (Arrays.stream(gas).sum() < Arrays.stream(cost).sum())
            return -1;
        int length = gas.length;
        Queue<Integer> queue = new LinkedList<>();
        int i = 0, totalGas = 0, totalCost = 0;
        while (queue.size() < length) {
            queue.add(i);
            totalGas += gas[i];
            totalCost += cost[i];
            while (!queue.isEmpty() && totalGas < totalCost) {
                int index = queue.remove();
                totalGas -= gas[index];
                totalCost -= cost[index];
            }
            i = (i + 1) % length;
        }
        return queue.remove();
    }

    //O(1) space complexity
    public int canCompleteCircuitO(int[] gas, int[] cost) {

        if (Arrays.stream(gas).sum() < Arrays.stream(cost).sum())
            return -1;

        int length = gas.length;
        int startIndex = 0, size = 0;
        int i = 0, totalGas = 0, totalCost = 0;
        while (size < length) {
            totalGas += gas[i];
            totalCost += cost[i];
            size++;
            while (size > 0 && totalGas < totalCost) {
                totalGas -= gas[startIndex];
                totalCost -= cost[startIndex];

                startIndex = (startIndex + 1) % length;
                size--;
            }
            i = (i + 1) % length;
        }
        return startIndex;
    }
}