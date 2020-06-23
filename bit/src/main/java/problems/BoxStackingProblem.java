package problems;

import java.util.ArrayList;
import java.util.Collections;

public class BoxStackingProblem {
    class Box implements Comparable<Box> {
        int length, width, height;
        Box(int l, int w, int h) {
            this.length = l;
            this.width = w;
            this.height = h;
        }

        boolean contains(Box box) {
            return (length > box.length && width > box.width) ||
                    (length > box.width && width > box.length);
        }

        @Override
        public int compareTo(Box box) {
            return Integer.compare(box.width*box.length,width*length);
        }
    }

    public ArrayList<Box> genListFrom(ArrayList<ArrayList<Integer>> array) {
        ArrayList<Box> boxList = new ArrayList<>();
        for(ArrayList<Integer> params : array) {
            boxList.add(new Box(params.get(0),params.get(1),params.get(2)));
            boxList.add(new Box(params.get(1),params.get(2),params.get(0)));
            boxList.add(new Box(params.get(0),params.get(2),params.get(1)));
        }
        return boxList;
    }

    int []cache;
    public int findMaxHeightFrom(int index, ArrayList<Box> array) {

        if(index >= array.size()) return 0;
        if(cache[index] != 0) return cache[index];

        Box current = array.get(index);
        int maxHeight = current.height;
        for(int i = index+1; i < array.size(); ++i) {
            if(current.contains(array.get(i)))
                maxHeight = Math.max(maxHeight,
                        findMaxHeightFrom(i, array) + current.height);
        }
        cache[index] = maxHeight;
        return cache[index];
    }

    public int solve(ArrayList<ArrayList<Integer>> A) {

        ArrayList<Box> boxList = genListFrom(A);
        Collections.sort(boxList);
        int maxHeight = 0;
        cache = new int[boxList.size()];
        for(int i = 0; i < boxList.size(); ++i) {
            maxHeight = Math.max(maxHeight, findMaxHeightFrom(i, boxList));
        }
        return maxHeight;
    }
}
