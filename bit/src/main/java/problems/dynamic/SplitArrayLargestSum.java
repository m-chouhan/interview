package problems.dynamic;

import java.util.HashMap;
import java.util.stream.Stream;

public class SplitArrayLargestSum {

    int [] array;
    HashMap<Integer, Integer>[][]cache;
    int [][] cache2;

    public int splitArray(int[] nums, int m) {
        array = nums;
        cache = new HashMap[nums.length][m];
        cache2 = new int[nums.length][m+1];
        for(int i = 0; i < nums.length; ++i)
            for(int j = 0; j < m+1; ++j)
                cache2[i][j] = -1;

        return minimumSplitValue2(nums.length - 1, m);
    }

    public int minimumSplitValue2(int currIndex, int m) {

        if(cache2[currIndex][m] != -1) return cache2[currIndex][m];
        HashMap<Float, Integer> [] map = new HashMap[2];
        int result;
        if(currIndex < (m-1) || m <= 0)
            result = Integer.MAX_VALUE;
        else if(m == 1) {
            int sum = 0;
            for(int i = 0; i <= currIndex; ++i) sum += array[i];
            result = sum;
        }
        else if(currIndex == (m-1)) {
            int maxValue = array[0];
            for (int i = 1; i <= currIndex; ++i)
                maxValue = Math.max(maxValue, array[i]);
            result = maxValue;
        } else {
            int minimumVal = Integer.MAX_VALUE;
            int sum = 0;
            for(int i = currIndex; i > 0; --i) {
                sum += array[i];
                minimumVal = Math.min(
                    Math.max(minimumSplitValue2(i-1,m-1), sum),
                    minimumVal
                );
            }
            result = minimumVal;
        }
        cache2[currIndex][m] = result;
        return result;
    }

    /**
     * @param n => currently processing value
     * @param m => as defined in solution
     * @param partitionSize => size of currently running partition
     * @return
     */
    public int minimumSplitValue(int n, int m, int partitionSize) {

        //edgecases
        if(n < m) return Integer.MAX_VALUE;
        if(m <= 0) return Integer.MAX_VALUE;

        HashMap<Integer, Integer> localCache = cache[n-1][m-1];
        if(localCache == null) {
            localCache = new HashMap<>();
            cache[n-1][m-1] = localCache;
        }

        if(localCache.containsKey(partitionSize))
            return localCache.get(partitionSize);

        if(n == m) {
            int maxVal = array[n-1] + partitionSize;
            for(int i = 0; i < n-1 ; ++i)
                maxVal = Math.max(maxVal, array[i]);
            localCache.put(partitionSize, maxVal);
        }
        else if(m == 1) {
            int sum = partitionSize;
            for(int i = 0; i < n; ++i)
                sum += array[i];
            localCache.put(partitionSize, sum);
        }
        else {
            //recursion
            int result =
                    Math.min(
                            Math.max(
                                    minimumSplitValue(n - 1, m - 1, 0),
                                    partitionSize + array[n - 1]
                            ),
                            minimumSplitValue(n - 1, m, partitionSize + array[n - 1])
                    );
            localCache.put(partitionSize, result);
        }
        return localCache.get(partitionSize);
    }
}
