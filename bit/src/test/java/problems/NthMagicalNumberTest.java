package problems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NthMagicalNumberTest {

    @Test
    public void shouldReturnCorrectNumber() {
        NthMagicalNumber nthMagicalNumber = new NthMagicalNumber();
        assertEquals(2, nthMagicalNumber.findMagicalNumberIn(new int[] {-5,0,2,4,5,6}));
        assertEquals(0, nthMagicalNumber.findMagicalNumberIn(new int[] {0,2,4,5,6}));
        assertEquals(3, nthMagicalNumber.findMagicalNumberIn(new int[] {-5,-2,-1,3}));
        assertEquals(-1, nthMagicalNumber.findMagicalNumberIn(new int[] {-5,-4,-3}));
    }
}
