package problems;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MinimumJumpsTest {

    MinimumJumps INSTANCE;

    @Before
    public void setUp() throws Exception {
        INSTANCE = new MinimumJumps();
    }

    @Test
    public void shouldReturnZeroOnEmptyInput() {

    }

    @Test
    public void shouldReturnNegativeIfNotRechable() {

        List<Integer> input = Arrays.asList(0, 3, 5, 8);
        int expectedOutput = -1;
        int actualOuput = INSTANCE.findMinJumpCount(input, 0);
        assert (actualOuput == expectedOutput);

        input = Arrays.asList(1, 0, 0);
        assert (INSTANCE.findMinJumpCount(input, 0) == expectedOutput);

        input = Arrays.asList(4, 0);
        assert (INSTANCE.findMinJumpCount(input, 0) == expectedOutput);
    }

    //@Test
    public void shouldReturnMinJumpsIndices() {
        List<Integer> input = Arrays.asList(1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9);
        List<Integer> expectedOutput = Arrays.asList(0, 1, 3, 10);
        List<Integer> actualOuput = INSTANCE.findJumpIndices(input);

        assert (actualOuput.equals(expectedOutput));

        input = Arrays.asList(2, 8, 3, 6, 9, 3, 0, 0, 1, 3);
        expectedOutput = Arrays.asList(0, 1, 9);
        actualOuput = INSTANCE.findJumpIndices(input);
        assert (actualOuput.equals(expectedOutput));
    }

    @Test
    public void shoudReturnMinJumpCount() {
        List<Integer> input = Arrays.asList(1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9);
        int expectedOutput = 4;
        int actualOuput = INSTANCE.findMinJumpCount(input, 0);

        assert (actualOuput == expectedOutput);

        input = Arrays.asList(2, 8, 3, 6, 9, 3, 0, 0, 1, 3);
        expectedOutput = 3;
        actualOuput = INSTANCE.findMinJumpCount(input, 0);
        assert (actualOuput == expectedOutput);

        input = Arrays.asList(1);
        assert (INSTANCE.findMinJumpCount(input, 0) == 1);

    }
}