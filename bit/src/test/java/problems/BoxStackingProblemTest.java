package problems;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoxStackingProblemTest {

    @Test
    public void shouldReturnMaxHeight() {
        BoxStackingProblem boxStackingProblem = new BoxStackingProblem();
        ArrayList<ArrayList<Integer>> input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(1,1,1)));
        assertEquals(1, boxStackingProblem.solve(input));
        input.add(new ArrayList<>(Arrays.asList(2,2,2)));
        assertEquals(3, boxStackingProblem.solve(input));
        input.add(new ArrayList<>(Arrays.asList(1,1,1)));
        assertEquals(3, boxStackingProblem.solve(input));
        input.add(new ArrayList<>(Arrays.asList(1,2,3)));
        assertEquals(4, boxStackingProblem.solve(input));
        input.add(new ArrayList<>(Arrays.asList(5,5,5)));
        assertEquals(9, boxStackingProblem.solve(input));
    }
}
