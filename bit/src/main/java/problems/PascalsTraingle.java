package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/pascals-triangle/description/
 */
public class PascalsTraingle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> output = new ArrayList<>();
        if(numRows <= 0) return output;
        output.add(Arrays.asList(1));

        for(int i = 1;i<numRows;++i) {
            List<Integer> previousList = output.get(output.size()-1);
            List<Integer> currentList = new ArrayList<>();
            int previous = 0;
            for(Integer current: previousList) {
                currentList.add(current+previous);
                previous = current;
            }
            currentList.add(previousList.get(previousList.size()-1));
            output.add(currentList);
        }
        return output;
    }
}
