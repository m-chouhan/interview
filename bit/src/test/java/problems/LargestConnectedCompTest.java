package problems;

import org.junit.Before;
import org.junit.Test;

public class LargestConnectedCompTest {

    private LargestConnectedComp INSTANCE;

    @Before
    public void setUp() {
        INSTANCE = new LargestConnectedComp();
    }

    @Test
    public void shouldReturnLargestConnectedComponent() {

        int input[][] = {
                {1, 4, 4, 4, 4, 3, 3, 1},
                {2, 1, 1, 4, 3, 3, 1, 1},
                {3, 2, 1, 1, 2, 3, 2, 1},
                {3, 3, 2, 1, 2, 2, 2, 2},
                {3, 1, 3, 1, 1, 4, 4, 4},
                {1, 1, 3, 1, 1, 4, 4, 4}
        };

        assert (INSTANCE.largestConnectedGrid(input) == 9);

        int input2[][] = {
                {1, 4, 4, 4, 4, 3, 3, 1},
                {2, 1, 1, 3, 3, 3, 1, 1},
                {3, 2, 1, 1, 2, 3, 2, 1},
        };

        assert (INSTANCE.largestConnectedGrid(input2) == 6);
    }
}