package problems;

import org.junit.Test;

import static org.junit.Assert.*;

public class NSweetMPersonTest {

    @Test
    public void shouldHandleBaseCase() {
        NSweetMPerson nSweetMPerson = new NSweetMPerson();

        int sweets[] = {1, 1, 1, 1};
        assertEquals(1, nSweetMPerson.findMinCost(1, 1, sweets));
        assertEquals(1, nSweetMPerson.findMinCost(2, 1, sweets));
        assertEquals(3, nSweetMPerson.findMinCost(3, 3, sweets));
    }

    @Test
    public void shouldHandleLargerCases() {

        int sweets[] = {2, 1, 3, 0, 4, 10};
        NSweetMPerson nSweetMPerson = new NSweetMPerson();
        assertEquals(3, nSweetMPerson.findMinCost(6, 3, sweets));

        int sweets2[] = {1, 3, 0, 5, 0, 0, 0};
        assertEquals(-1, nSweetMPerson.findMinCost(7, 2, sweets2));
        assertEquals(-1, nSweetMPerson.findMinCost(5, 1, sweets2));
        assertEquals(5, nSweetMPerson.findMinCost(4, 1, sweets2));
        assertEquals(9, nSweetMPerson.findMinCost(7, 3, sweets2));
        assertEquals(8, nSweetMPerson.findMinCost(7, 4, sweets2));

    }
}