package problems;

import org.junit.Test;
import problems.DataStructures.Heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeapTest {

    ArrayList<Integer> transfromInputUsingHeap(List<Integer> input) {
        Heap heap = new Heap();
        input.forEach(item -> heap.push(item));

        ArrayList<Integer> output = new ArrayList<>();
        while (heap.size() != 0) {
            output.add(heap.pop().value);
        }
        return output;
    }

    @Test
    public void shouldReturnSortedArray() {
        List<Integer> input = Arrays.asList(6, 5, 4, 3, 2);
        List<Integer> expectedOutput = Arrays.asList(2, 3, 4, 5, 6);

        ArrayList<Integer> output = transfromInputUsingHeap(input);
        assert (output.equals(expectedOutput));
    }

    @Test
    public void shouldReturnMinValue() {
        Heap heap = new Heap();

        heap.push(0);
        assert (heap.getMin() == 0);

        heap.push(1);
        assert (heap.getMin() == 0);

        heap.push(-1);
        assert (heap.getMin() == -1);

        heap.push(-1);
        assert (heap.getMin() == -1);
        assert (heap.size() == 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionIfEmpty() {
        Heap heap = new Heap();
        heap.getMin();
    }
}