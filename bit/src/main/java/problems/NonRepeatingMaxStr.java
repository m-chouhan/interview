package problems;

import java.util.ArrayList;
import java.util.HashMap;

public class NonRepeatingMaxStr {

    public static void main(String args[]) {

        String input[] = {"ABDEFGABEF", "GEEKSFORGEEKS", "BBBB"};
        for (String in : input)
            System.out.println(in + " : " + findMaxLength(in));
    }

    private static int findMaxLength(String input) {
        char charArray[] = input.toCharArray();

        HashMap<Character, Integer> lastSeen = new HashMap<>();
        ArrayList<Integer> maxAt = new ArrayList<>();

        lastSeen.put(charArray[0], 0);
        maxAt.add(1);

        for (int index = 1; index < charArray.length; ++index) {

            int len1 = (lastSeen.get(charArray[index]) != null) ?
                    index - lastSeen.get(charArray[index])
                    : index + 1;
            int len2 = maxAt.get(index - 1) + 1;
            maxAt.add(Math.min(len1, len2));
            lastSeen.put(charArray[index], index);
        }

        int maxLen = maxAt.get(0);
        for (int len : maxAt) {
            if (len > maxLen) maxLen = len;
        }
        
        return maxLen;
    }
}
