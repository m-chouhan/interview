package problems;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class FindUniquePairSumTest {

    //empty input => empty output
    @Test
    public void EmptyInputTest() {
        List<Integer> inputArray = Arrays.asList();
        Integer inputNum = 10;

        FindPairSum findPairSum = new FindPairSum();

        HashSet<FindPairSum.UniquePair> uniquePairSet = findPairSum.findPairs(inputArray, inputNum);
        assert (uniquePairSet.size() == 0);
    }

    //what happens if duplicates are present ?
    @Test
    public void DuplicatValuesTest() {
        List<Integer> inputArray = Arrays.asList(
                1, 1, 2, 2, 4, 4, 3, 5
        );

        FindPairSum findPairSum = new FindPairSum();

        HashSet<FindPairSum.UniquePair> uniquePairSet = findPairSum.findPairs(inputArray, 10);
        assert (uniquePairSet.size() == 0);

        uniquePairSet = findPairSum.findPairs(inputArray, 2);
        assert (uniquePairSet.size() == 1);

        uniquePairSet = findPairSum.findPairs(inputArray, 8);
        assert (uniquePairSet.size() == 2);

        assert (uniquePairSet.contains(new FindPairSum.UniquePair(4, 4)));
        assert (uniquePairSet.contains(new FindPairSum.UniquePair(3, 5)));
        assert (!uniquePairSet.contains(new FindPairSum.UniquePair(5, 3)));
    }

    //what happens if negative values are present ?
    @Test
    public void NegativeValues() {
        List<Integer> inputArray = Arrays.asList(
                1, -1, 2, -5, 4, 4, 3, 5
        );

        FindPairSum findPairSum = new FindPairSum();

        HashSet<FindPairSum.UniquePair> uniquePairSet = findPairSum.findPairs(inputArray, 0);
        assert (uniquePairSet.size() == 2);
        assert (uniquePairSet.contains(new FindPairSum.UniquePair(-1, 1)));
        assert (uniquePairSet.contains(new FindPairSum.UniquePair(-5, 5)));
    }

    @Test
    public void BigNumbers() {
        List<Integer> inputArray = Arrays.asList(
                1000000, 312312312, 0, -5, 121231114, 4, 3, 5
        );
        FindPairSum findPairSum = new FindPairSum();

        HashSet<FindPairSum.UniquePair> uniquePairSet = findPairSum.findPairs(inputArray, 312312312);
        assert (uniquePairSet.size() == 1);
    }

}

