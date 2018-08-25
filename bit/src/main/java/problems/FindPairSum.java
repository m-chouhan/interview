package problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/***
 * Given an array A of integers and a number X,
 * find 'unique' pairs (a,b) : a + b = X && a,b E A
 */
public class FindPairSum {

    static class Pair {
        int left, right;

        Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Pair) {
                Pair p = (Pair) o;
                return (p.left == left && p.right == right) ||
                        (p.right == left && p.left == right);
            }
            return false;
        }

        //since left is the smaller number
        @Override
        public int hashCode() {
            return left;
        }

    }

    public static void main(String args[]) {

        List<Integer> inputArray = Arrays.asList(
                1, 2, -2, 3, 4, -6, 16, 5, 5, 12, 1, 11, -11, 15, 16, 17
        );

        Integer inputNum = 10;

        HashSet<Pair> pairSet = findPairs(inputArray, inputNum);
        if (pairSet.size() == 0) {
            System.out.print("No pairs found :(");
            return;
        }
        pairSet.forEach(pair ->
                System.out.println("Found pair " + pair.left + "," + pair.right)
        );
    }

    static HashSet<Pair> findPairs(List<Integer> inputArray, Integer num) {
        //TODO : handle duplicates properly

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        //initialize hashMap
        for (int val : inputArray) {

            if (hashMap.get(val) == null) hashMap.put(val, 1);
            else hashMap.put(val, hashMap.get(val) + 1);
        }

        HashSet<Pair> output = new HashSet<>();

        for (int val : inputArray) {
            int othernum = num - val;
            if (othernum == val && hashMap.get(val) == 1) continue;
            if (hashMap.get(othernum) == null) continue;
            output.add(
                    new Pair(Math.min(othernum, val),
                            Math.max(othernum, val))
            );
        }
        return output;
    }
}
