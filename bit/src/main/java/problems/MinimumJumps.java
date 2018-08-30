package problems;

import java.util.List;

public class MinimumJumps {


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
}
