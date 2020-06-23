package problems;


import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpiralMatrix {

    final int right = 0;
    final int down = 1;
    final int left = 2;
    final int up = 3;

    boolean visited[][];
    int [][] matrix;
    //initialization
    List<Integer> output;

    public void init(int [][] matrix) {
        this.matrix = matrix;
        visited = new boolean[matrix.length][matrix[0].length];
        for(boolean[] arr: visited) Arrays.fill(arr,false);
        output = new ArrayList<>();
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix == null ||
                matrix.length == 0 ||
                matrix[0].length == 0 )
            return new ArrayList<>();
        init(matrix);
        DFS(0,0,0);
        return output;
    }

    public List<Integer> spiralOrder2(int [][] matrix) {
        if(matrix == null ||
                matrix.length == 0 ||
                matrix[0].length == 0 )
            return new ArrayList<>();
        init(matrix);
        int startI = 0,startJ = 0,endI = matrix.length-1,endJ = matrix[0].length-1;
        while(startI < endI && startJ < endJ) {
            output.addAll(singleLayer(startI,startJ,endI,endJ));
            startI++;startJ++;
            endI--;endJ--;
        }
        if(startI == endI) {
            while(startJ <= endJ) output.add(matrix[startI][startJ++]);
        }
        else if(startJ == endJ) {
            while(startI <= endI) output.add(matrix[startI++][startJ]);
        }
        return output;
    }
    //both endpoints are inclusive
    public List<Integer> singleLayer(int startI,int startJ,int endI,int endJ) {
        ArrayList<Integer> output = new ArrayList<>();

        //return null
        if(startI > endI || startJ > endJ) return output;
        if(startI == endI && startJ == endJ) {
            output.add(matrix[startI][startJ]);
            return output;
        }

        //right
        for(int j = startJ;j <= endJ;++j) {
            output.add(matrix[startI][j]);
        }
        //down
        for(int i = startI+1;i<=endI;++i) {
            output.add(matrix[i][endJ]);
        }
        //left
        for(int j = endJ-1;j >= startJ;--j) {
            output.add(matrix[endI][j]);
        }
        //up
        for(int i = endI-1;i > startI;--i) {
            output.add(matrix[i][startJ]);
        }
        return output;
    }

    void DFS(int i,int j,int dir) {
        if(i < 0 || j < 0 || i>=matrix.length || j >= matrix[0].length)
            return;
        if(visited[i][j]) return;
        System.out.print(matrix[i][j]);
        output.add(matrix[i][j]);
        visited[i][j] = true;

        for(int nextDir = 0;nextDir<4;++nextDir)
        {
            switch ((dir+nextDir)%4) {
                case left:
                    DFS(i,j-1,left);
                    break;
                case right:
                    DFS(i,j+1,right);
                    break;
                case up:
                    DFS(i-1,j,up);
                    break;
                case down:
                    DFS(i+1,j,down);
                    break;
            }
        }
    }
}
