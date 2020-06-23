package problems;

import java.util.*;
import java.util.stream.Collectors;

public class TrapRainWaterII {

    static int width, height;

    public class Cell {
        int value;
        int row,col;

        public Cell(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Cell) return ((Cell) obj).col == col && ((Cell) obj).row == row;
            return false;
        }

        @Override
        public int hashCode() {
            return row * width + col;
        }
    }

    public int trapRainWater(int[][] heightMap) {
        height = heightMap.length;
        width = heightMap[0].length;

        PriorityQueue<Cell> pQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.value));
        HashSet<Cell> visited = new HashSet<>();
        Cell [][] grid = new Cell[height][width];
        for(int i = 0; i < height; ++i) for (int j = 0; j < width; ++j)
        {
            grid[i][j] = new Cell(i,j, heightMap[i][j]);
            if(i == 0 || i == height-1 || j == 0 || j == width-1) {
                pQueue.add(grid[i][j]);
                visited.add(grid[i][j]);
            }
        }

        int wAmount = 0;
        while (!pQueue.isEmpty()) {
            Cell cell = pQueue.remove();
            List<Cell> list = getUnvisitedNeighbours(cell, grid, visited);
            for(Cell neighbour : list) {
                visited.add(neighbour);
                wAmount += Math.max(0, cell.value - neighbour.value);
                neighbour.value = Math.max(cell.value, neighbour.value);
                pQueue.add(neighbour);
            }
        }
        return wAmount;
    }

    private List<Cell> getUnvisitedNeighbours(Cell cell, Cell[][] grid, HashSet<Cell> visited) {
        List<Cell> list = new ArrayList<>();
        if(cell.row > 0 && !visited.contains(grid[cell.row-1][cell.col]))
            list.add(grid[cell.row-1][cell.col]);
        if(cell.col > 0 && !visited.contains(grid[cell.row][cell.col-1]))
            list.add(grid[cell.row][cell.col-1]);
        if(cell.row < height-1 && !visited.contains(grid[cell.row+1][cell.col]))
            list.add(grid[cell.row+1][cell.col]);
        if(cell.col < width-1 && !visited.contains(grid[cell.row][cell.col+1]))
            list.add(grid[cell.row][cell.col+1]);
        return list;
    }
}
