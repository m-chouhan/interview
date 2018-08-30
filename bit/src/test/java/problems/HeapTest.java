package problems;

import org.junit.Test;

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
    public void shouldHandleEmptyOrSingleInput() {
        List<Integer> input = Arrays.asList();
        ArrayList<Integer> output = transfromInputUsingHeap(input);
        assert (output.size() == 0);
        output = transfromInputUsingHeap(Arrays.asList(1));
        assert (output.size() == 1);
    }

    @Test
    public void shouldReturnSortedArray() {
        List<Integer> input = Arrays.asList(6, 5, 4, 3, 2);
        List<Integer> expectedOutput = Arrays.asList(2, 3, 4, 5, 6);

        ArrayList<Integer> output = transfromInputUsingHeap(input);
        assert (output.equals(expectedOutput));
    }

}