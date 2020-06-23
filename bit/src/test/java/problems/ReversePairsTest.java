package problems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReversePairsTest {

    @Test
    void reversePairs() {
        ReversePairs reversePairs = new ReversePairs();
        assertEquals(2,
                reversePairs.reversePairs(new int [] {1,3,2,3,1}));
        assertEquals(3,
                reversePairs.reversePairs(new int [] {2,4,3,5,1}));
        assertEquals(0,
                reversePairs.reversePairs(new int [] {1,1,1,1}));
        assertEquals(0,
                reversePairs.reversePairs(new int [] {2147483647,2147483647,2147483647,2147483647,2147483647,2147483647}));
    }
}