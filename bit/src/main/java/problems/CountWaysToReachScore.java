package problems;

import java.util.List;


/**
 * https://www.geeksforgeeks.org/count-number-ways-reach-given-score-game/
 */
public class CountWaysToReachScore {

    final List<Integer> possibleScores;

    public CountWaysToReachScore(List<Integer> possibleScores) {
        this.possibleScores = possibleScores;
    }

    /**
     * Broken, will return all permutations as well
     *
     * @param value
     * @return possible premutations to reach that count
     */
    public int countFor$(int value) {
        if (value == 0) return 1;
        return possibleScores
                .stream()
                .map(integer -> {
                    if (integer <= value)
                        return countFor$(value - integer);
                    return 0;
                })
                .reduce(0, (prev, current) -> prev + current);
    }

    /**
     * Returns possible count that can be acheived by
     * using only 'index' number of items with repetition allowed
     *
     * @param value
     * @param index
     * @return
     */
    public int countFor(int value, int index) {
        //invalid index check
        if (index < 0 || index >= possibleScores.size() || value < 0) return 0;

        //only one way i.e select none
        if (value == 0) return 1;

        int excludeSum = countFor(value, index - 1);
        int item = possibleScores.get(index);
        int includeSum = countFor(value - item, index);

        return excludeSum + includeSum;
    }
}
