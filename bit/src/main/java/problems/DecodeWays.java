package problems;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/decode-ways
 */
class DecodeWays {

    public int numDecodingsComplex(String str) {
        if (str == null || str.length() == 0) return 0;
        HashMap<String, Integer> map = new HashMap<>();

        map.put("0", 0);
        map.put("", 0);
        for (int i = 1; i <= 99; ++i)
            if (i <= 10 || i == 20)
                map.put(String.valueOf(i), 1);
            else if (i <= 26)
                map.put(String.valueOf(i), 2);
            else if (i % 10 == 0)
                map.put(String.valueOf(i), 0);
            else map.put(String.valueOf(i), 1);
        for (int i = 0; i <= 9; ++i)
            map.put("0" + i, 0);

        return numDecodings(str, map);
    }

    public int numDecodings(String str, HashMap<String, Integer> map) {
        if (str.length() == 0) return 0;
        if (map.containsKey(str)) return map.get(str);

        String oneLeft = str.substring(0, str.length() - 1);
        int oneCount =
                str.charAt(str.length() - 1) != '0' ? numDecodings(oneLeft, map) : 0;

        String twoRight = str.substring(str.length() - 2);
        String twoLeft = str.substring(0, str.length() - 2);
        int twoCount = numDecodings(twoLeft, map) * numDecodings(twoRight, map);
        return oneCount + twoCount;
    }

    public static void main(String string[]) {
        DecodeWays decodeWays = new DecodeWays();
        decodeWays.numDecodingsComplex("223");
    }

    public int numDecodingsArraySolution(String str) {

        if (str == null || str.length() == 0) return 0;
        int cache[] = new int[str.length() + 1];
        cache[0] = 1;

        for (int i = 1; i <= str.length(); ++i) {
            int countI = 0;
            if (str.charAt(i - 1) != '0')
                countI += cache[i - 1];
            if (i > 1 && validToken(str, i))
                countI += cache[i - 2];
            cache[i] = countI;
        }

        return cache[str.length()];
    }

    /*
        returns true if a valid size_2 token is present
        starting at given index
    */
    boolean validToken(String string, int len) {
        return string.charAt(len - 2) == '1' ||
                (string.charAt(len - 2) == '2' && string.charAt(len - 1) <= '6');
    }
}