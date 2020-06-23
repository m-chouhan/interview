package problems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplyPermutationTest {

    @Test
    void apply() {
        ApplyPermutation permutation = new ApplyPermutation();

        assertArrayEquals(new int[] {4,3,2,1}, permutation.apply(new int[] {1,2,3,4}, new int[]{3,2,1,0}));
        assertArrayEquals(new int[] {3,42,11,32}, permutation.apply(new int[] {11,32,3,42}, new int[]{2,3,0,1}));
    }
}