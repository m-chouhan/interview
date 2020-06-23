package problems;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class CountWaysToReachScoreTest {

    @Test
    public void shouldReturnCorrectOutput() {
        List<Integer> possibleScores = Arrays.asList(3, 5, 10);
        CountWaysToReachScore instance = new CountWaysToReachScore(possibleScores);
        assert (instance.countFor$(3) == 1);
        assert (instance.countFor$(5) == 1);
        assert (instance.countFor$(10) == 2);
        assert (instance.countFor$(6) == 1);

        System.out.println(instance.countFor$(20));
        System.out.println(instance.countFor(20, 2));

    }
}