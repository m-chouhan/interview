package problems;

import java.util.Stack;

/**
 * For each item store next largest number of the array
 */
public class NextLargestNum {

    static int[] largestArray(int array[]) {
        Stack<Integer> stack = new Stack<>();
        int[] output = new int[array.length];
        int index = 0;
        stack.push(index++);

        while (index < array.length) {
            while (index < array.length && array[stack.peek()] > array[index])
                stack.push(index++);
            if (index == array.length) break;
            while (!stack.isEmpty() && stack.peek() < array[index]) {
                output[stack.pop()] = array[index];
            }
            stack.push(index++);
        }
        while (!stack.isEmpty())
            output[stack.pop()] = -1;
        return output;
    }

    public static void main(String[] args) {
        int array[] = {1, 2, 3, 9, 5, 4, 10, 11, 12, 6, 5, 4, 3};
        int output[] = largestArray(array);
        for (int i = 0; i < output.length; ++i) {
            System.out.print(" " + output[i]);
        }
    }
}
