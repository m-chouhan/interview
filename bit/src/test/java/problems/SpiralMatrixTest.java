package problems;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SpiralMatrixTest {

    @Test
    public void shouldPrintMatrixInSpiralOrder(){
        SpiralMatrix spiralMatrix = new SpiralMatrix();
        int [][]matrix = {
            {
                1, 2, 3
            },
            {
                4, 5, 6
            },
            {
                7, 8, 9
            }
        };

        List<Integer> expectedOutput = Arrays.asList(1,2,3,6,9,8,7,4,5);
        List<Integer> actualOut = spiralMatrix.spiralOrder2(matrix);
        assert (expectedOutput.equals(actualOut));
        spiralMatrix.spiralOrder(new int[0][1]);
    }
}