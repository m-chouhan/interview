package problems;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * TODO: improve the implementation
 */
public class LargestConnectedComp {

    class Pair {
        int i;
        int j;

        Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    List<Pair> neighbours = Arrays.asList(
            new Pair(1, 0),
            new Pair(-1, 0),
            new Pair(0, 1),
            new Pair(0, -1)
    );

    private final int VISITED = -1;

    int findRegionSize(int[][] input, Pair pair) {

        if (input[pair.i][pair.j] == VISITED)
            return 0;

        int size = 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(pair);
        while (!queue.isEmpty()) {
            Pair item = queue.remove();
            int currentId = input[item.i][item.j];
            //node already visited, dont do anything
            if (currentId == VISITED)
                continue;

            input[item.i][item.j] = VISITED;
            size++;
            //Add neighbours to queue if valid
            for (Pair neighbour : neighbours) {
                int i = item.i + neighbour.i;
                int j = item.j + neighbour.j;
                if (i >= 0 && i < input.length &&
                        j >= 0 && j < input[i].length &&
                        input[i][j] == currentId)
                    queue.add(new Pair(i, j));
            }
        }
        return size;
    }

    public int largestConnectedGrid(int[][] input) {

        int maxSize = 0;
        int[][] grid = new int[input.length][];
        for (int i = 0; i < input.length; i++) {
            grid[i] = input[i].clone();
        }

        for (int i = 0; i < input.length; ++i) {
            for (int j = 0; j < input[i].length; ++j) {
                if (input[i][j] != -1) {
                    int localSize = findRegionSize(grid, new Pair(i, j));
                    if (maxSize < localSize) maxSize = localSize;
                }
            }
        }
        return maxSize;
    }
}
