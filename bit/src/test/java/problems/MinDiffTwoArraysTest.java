package problems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinDiffTwoArraysTest {

    @Test
    void minDiff() {
        MinDiffTwoArrays testClass = new MinDiffTwoArrays();
        assertEquals(-1, testClass.minDiff(new int[]{}, new int[]{}));
        assertEquals(-1, testClass.minDiff(new int[]{}, null));
        assertEquals(-1, testClass.minDiff(null, new int[]{}));

        assertEquals(0, testClass.minDiff(new int[]{0}, new int[]{0}));
        assertEquals(1, testClass.minDiff(new int[]{0}, new int[]{1}));
        assertEquals(3, testClass.minDiff(new int[]{0,2}, new int[]{15,5}));

        assertEquals(5, testClass.minDiff(new int[]{0,10,60,90}, new int[]{50,100,5}));
        assertEquals(5, testClass.minDiff(new int[]{30,10,60,90}, new int[]{50,100,5}));
        assertEquals(10, testClass.minDiff(new int[]{30,19,60,90}, new int[]{50,101,5}));
        assertEquals(5, testClass.minDiff(new int[]{30,19,60,90}, new int[]{50,101,95}));
    }
}