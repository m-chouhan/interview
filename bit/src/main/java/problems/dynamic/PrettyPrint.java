package problems.dynamic;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrettyPrint {

    String [] array;
    int maxWidth;
    Pair<Integer, Integer> []cache;

    List<String> prettyPrint(String input, int width) {
        maxWidth = width;
        array = input.split("\\s+");
        if(array.length == 0) return null;

        cache = new Pair[array.length];
        cache[0] = new Pair((int)Math.pow(maxWidth - array[0].length(), 3), 0);
        //assume forAll word : words => word.length <= maxWidth
        for(int i = 1; i < array.length; ++i) {
            int currentLength = array[i].length();
            int spaces = maxWidth - currentLength;
            int minCost = (int) (Math.pow(spaces, 3) + cache[i-1].getKey());
            int breakPoint = i;

            for(int j = i-1; j >= 0 && spaces >= array[j].length() + 1; j--) {
                spaces -= array[j].length() + 1;
                int localMin = (int) (Math.pow(spaces, 3) + (j == 0 ? 0 : cache[j-1].getKey()));
                if(localMin < minCost) {
                    minCost = localMin;
                    breakPoint = j;
                }
            }
            cache[i] = new Pair<>(minCost, breakPoint);
        }

        return buildAnswerFrom(cache);
    }

    private List<String> buildAnswerFrom(Pair<Integer, Integer>[] cache) {
        ArrayList<String> ans = new ArrayList<>();
        int index = array.length - 1;
        while(index >= 0) {
            StringBuilder builder = new StringBuilder();
            int breakPoint = cache[index].getValue();
            while (breakPoint < index) {
                builder.append(array[breakPoint]).append(" ");
                breakPoint++;
            }
            builder.append(array[index]);
            ans.add(builder.toString());
            index = cache[index].getValue() - 1;
        }
        Collections.reverse(ans);
        return ans;
    }

    Pair<Integer, Integer> findOptimalTill(int index) {
        if(index < 0) return new Pair<>(0,0);

        if(cache[index] != null) return cache[index];
        if(index == 0) {
            int spaces = maxWidth - array[index].length();
            cache[index] = new Pair<>(spaces*spaces*spaces, 0);
            return cache[index];
        }

        Pair<Integer, Integer> minPair = new Pair(Integer.MAX_VALUE, -1);

        for(int i = index, currentLength = 0; i >= 0; --i) {
            currentLength += array[i].length() + ((i == index) ? 0 : 1);
            int spaces = maxWidth - currentLength;
            if(spaces < 0) break;
            Pair<Integer, Integer> prevOptimal = findOptimalTill(i-1);
            int currentCost = spaces*spaces*spaces + prevOptimal.getKey();
            if(currentCost < minPair.getKey()) minPair = new Pair<>(currentCost, i);
        }
        cache[index] = minPair;
        return cache[index];
    }
}
