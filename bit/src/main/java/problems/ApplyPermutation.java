package problems;

import java.util.PriorityQueue;

public class ApplyPermutation {

    int [] apply(int [] array, int [] permute) {
        if(array == null || permute == null) return null;

        for(int i = 0; i < array.length; ++i) {
            int realIndex = permute[i];
            while(realIndex != i) {
                swap(array, realIndex, i);
                swap(permute, realIndex, i);
                realIndex = permute[i];
            }
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a,b) -> b-a);
        priorityQueue.remove();
        return array;
    }

    void swap(int [] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
