
package problems;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static class Floor {
        long rows;
        long columns;
        long size;

        Floor(long rows, long columns) {
            this.rows = rows;
            this.columns = columns;
            size = 0;
        }

        public boolean isFilled() {
            return size >= rows * columns;
        }

        public void push() {

        }
    }

    static void pushInto(ArrayList<Floor> floors) {
        if (floors.size() == 0) return;
        int floorIndex = 0;
        Floor f = floors.get(floorIndex);
        while (floors.get(floorIndex).isFilled()) floorIndex++;
    }

    static void popFrom(ArrayList<Floor> floors, long floor, long row, long column) {

    }

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        long floors = sc.nextLong();
        long rows = sc.nextLong();
        long columns = sc.nextLong();
        long N = sc.nextLong();
        ArrayList<Floor> Floors = new ArrayList<>();

        for (int i = 0; i < floors; ++i) {
            Floors.add(new Floor(rows, columns));
        }

        while (N-- > 0) {
            long status = sc.nextLong();
            if (status == 1)
                pushInto(Floors);
            else {
                long floor = sc.nextLong();
                long row = sc.nextLong();
                long column = sc.nextLong();
                popFrom(Floors, floor, row, column);
            }
        }
    }

}