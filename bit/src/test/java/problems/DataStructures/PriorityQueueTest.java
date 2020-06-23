package problems.DataStructures;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PriorityQueueTest {

    ArrayList<Integer> transfromInputUsingHeap(List<Integer> input) {
        PriorityQueue priorityQueue = new PriorityQueue(10);
        input.forEach(item -> priorityQueue.insert(item, item));

        ArrayList<Integer> output = new ArrayList<>();
        while (priorityQueue.size() != 0) {
            output.add(priorityQueue.getNextItem().value);
        }
        return output;
    }

    @Test
    public void shouldReturnSortedArray() {
        List<Integer> input = Arrays.asList(6, 5, 4, 3, 2);
        List<Integer> expectedOutput = Arrays.asList(2, 3, 4, 5, 6);

        ArrayList<Integer> output = transfromInputUsingHeap(input);
        assert (output.equals(expectedOutput));
        System.out.println(expectedOutput);
    }

    @Test
    public void shouldReturnMinValue() {
        PriorityQueue priorityQueue = new PriorityQueue(10);

        priorityQueue.insert(0, 10);
        assert (priorityQueue.getNextItem().value == 10);

        priorityQueue.insert(1, 20);
        assert (priorityQueue.peekMin().value == 20);
        assert (priorityQueue.peekMin().priority == 1);

        priorityQueue.insert(0, 100);
        assertEquals(100, priorityQueue.peekMin().value);
        assertEquals(0, priorityQueue.peekMin().priority);
        assertEquals(2, priorityQueue.size());
    }

    @Test
    public void indicesShouldBeInSync() {
        PriorityQueue priorityQueue = new PriorityQueue(10);
        for (int i = 10; i > 0; --i)
            priorityQueue.insert(i, i);

        assertEquals(priorityQueue.peekMin().index, 0);
        for (int i = 0; i < 10; ++i) {
            assertEquals(priorityQueue.peekMin().index, 0);
            PriorityQueue.Node[] allItems = priorityQueue.getAllItems();
            for (int j = 0; j < priorityQueue.size(); ++j) {
                assertEquals(allItems[j].index, j);
            }
            priorityQueue.getNextItem();
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionIfEmpty() {
        PriorityQueue heap = new PriorityQueue(10);
        heap.peekMin();
    }

    @Test
            (expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionIfFull() {
        PriorityQueue priorityQueue = new PriorityQueue(0);
        priorityQueue.insert(0, 0);
    }

}