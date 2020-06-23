package problems.divideAndConquer;

import java.util.Arrays;
import java.util.Stack;

/***
 * GOOGLE
 * Find largest area rectangle under histogram
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 */
class LargestHistogram {

    public int largestRectangleAreaStack(int[] heights) {
        if (heights == null || heights.length == 0)
            return 0;
        if (heights.length == 1) return heights[0];

        Stack<Integer> stack = new Stack<>();
        int maxAt[] = new int[heights.length];

        for (int i = 0; i < heights.length; ++i) {
            if (stack.isEmpty() || heights[stack.peek()] <= heights[i])
                stack.push(i);
            else {
                while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                    //pop and calculate area at that index
                    int index = stack.pop();
                    int lastLessOrEqual = stack.isEmpty() ? -1 : stack.peek();
                    maxAt[index] = (i - (lastLessOrEqual + 1)) * heights[index];
                }
                stack.push(i);
            }
        }

        while (!stack.isEmpty()) {
            int index = stack.pop();
            int lastLessOrEqual = stack.isEmpty() ? -1 : stack.peek();
            maxAt[index] = (heights.length - (lastLessOrEqual + 1)) * heights[index];
        }
        return Arrays.stream(maxAt)
                .max().getAsInt();
    }

    public int largestRectangleAreaDivideAndCon(int[] heights) {
        if (heights == null || heights.length == 0)
            return 0;
        if (heights.length == 1) return heights[0];
        return largestArea(0, heights.length - 1, heights);
    }

    public int largestArea(int start, int end, int[] heights) {
        if (start > end)
            return 0;
        if (start == end)
            return heights[start];
        if (start == end - 1)
            return Math.max(
                    Math.min(heights[start], heights[end]) * 2,
                    Math.max(heights[start], heights[end])
            );

        int minIndex = findMin(start, end, heights);
        int minIncArea = heights[minIndex] * (end - start + 1);
        int leftMaxArea = largestArea(start, minIndex - 1, heights);
        int rightMaxArea = largestArea(minIndex + 1, end, heights);
        return Math.max(minIncArea,
                Math.max(leftMaxArea, rightMaxArea));
    }

    int findMin(int start, int end, int[] heights) {
        int minIndex = start;
        for (int i = start + 1; i <= end; ++i)
            if (heights[minIndex] > heights[i])
                minIndex = i;
        return minIndex;
    }
}