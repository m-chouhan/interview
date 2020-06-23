package problems.dynamic;

import org.junit.Test;

import static org.junit.Assert.*;

public class WeightScheduleTest {

    @Test
    public void shouldReturnMaxWeight() {
        int[][] schedules = {{1, 2, 1}, {2, 3, 1}, {3, 4, 1}};
        int max = WeightSchedule.findMaxWeight(schedules);

        assertEquals(3, max);

        int[][] schedules2 = {{1, 2, 1}, {2, 5, 10}, {3, 4, 1}};
        max = WeightSchedule.findMaxWeight(schedules2);
        assertEquals(11, max);

        int[][] schedules3 = {{1, 12, 15}, {2, 5, 10}, {3, 4, 1}};
        max = WeightSchedule.findMaxWeight(schedules3);
        assertEquals(15, max);

        int[][] schedules4 = {{1, 12, 15}};
        max = WeightSchedule.findMaxWeight(schedules3);
        assertEquals(15, max);

    }
}