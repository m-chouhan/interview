package problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/***
 * Given an array A of integers and a number X,
 * find 'unique' pairs (a,b) : a + b = X && a,b E A
 */
public class FindPairSum {

    public static class UniquePair {

        int left, right;

        UniquePair(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof UniquePair) {
                UniquePair p = (UniquePair) o;
                //can be optimized ?
                return (p.left == left || p.right == left || p.left == right || p.right == right);
            }
            return false;
        }

        //each num only exists in exactly 'one solution'
        @Override
        public int hashCode() {
            return left;
        }
    }

    public static void main(String args[]) {

    }

    HashSet<UniquePair> findPairs(List<Integer> inputArray, Integer num) {
        //TODO : handle duplicates properly
        // DONE
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        //initialize hashMap
        for (int val : inputArray) {

            if (hashMap.get(val) == null) hashMap.put(val, 1);
            else hashMap.put(val, hashMap.get(val) + 1);
        }

        HashSet<UniquePair> output = new HashSet<>();

        for (int val : inputArray) {
            int othernum = num - val;
            if (othernum == val && hashMap.get(val) == 1) continue;
            if (hashMap.get(othernum) == null) continue;
            output.add(
                    new UniquePair(Math.min(othernum, val),
                            Math.max(othernum, val))
            );
        }
        return output;
    }
}
