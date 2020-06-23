package problems.dynamic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class SplitArrayLargestSumTest {

    @Test
    public void splitArrayTest() {
        SplitArrayLargestSum splitArrayLargestSum = new SplitArrayLargestSum();

        assertEquals(10, splitArrayLargestSum.splitArray(
                new int []{10}, 1)
        );

        assertEquals(10, splitArrayLargestSum.splitArray(
                new int []{5,3,2}, 1)
        );

        assertEquals(5, splitArrayLargestSum.splitArray(
                new int []{5,3,1}, 2)
        );

        assertEquals(5, splitArrayLargestSum.splitArray(
                new int []{5,3,2}, 3)
        );

        assertEquals(6, splitArrayLargestSum.splitArray(
                new int []{1,5,3,2,0,0}, 2)
        );

        assertEquals(5, splitArrayLargestSum.splitArray(
                new int []{1,5,3,2,0,0}, 3)
        );

        assertEquals(10, splitArrayLargestSum.splitArray(
                new int []{10,5,3}, 2)
        );

    }
}