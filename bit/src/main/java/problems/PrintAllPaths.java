package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PrintAllPaths {

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> output = new ArrayList<>();
        
        if (graph.length == 1) {
            output.add(Arrays.asList(0));
            return output;
        }
        //initialization
        int[] frames = new int[graph.length];
        int startNode = 0, lastNode = graph.length - 1;
        Stack<Integer> stack = new Stack<>();
        stack.push(startNode);
        while (!stack.isEmpty()) {
            if (stack.peek() == lastNode) {
                ArrayList<Integer> list = new ArrayList<>();
                list.addAll(stack);
                output.add((Stack<Integer>) stack.clone());
                stack.pop();
                if (stack.size() > 0)
                    frames[stack.size() - 1]++;
            }
            if (graph[stack.peek()].length <= frames[stack.size() - 1]) {
                stack.pop();
                if (stack.size() > 0)
                    frames[stack.size() - 1]++;
            } else {
                int node = graph[stack.peek()][frames[stack.size() - 1]];
                stack.push(node);
                frames[stack.size() - 1] = 0;
            }
        }
        return output;
    }

    public static void main(String[] args) {
        PrintAllPaths printAllPaths = new PrintAllPaths();
        int[][] graph = {{1, 2}, {3}, {3}, {}};
        List<List<Integer>> output = printAllPaths.allPathsSourceTarget(graph);
        output.forEach(
                list -> {
                    list.forEach(item -> System.out.print(item + " "));
                    System.out.println();
                }
        );
    }
}
