package problems.dynamic;

import java.util.Arrays;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/regular-expression-matching/
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 */

public class RegularExpressionMatching {
    String input, pattern;
    int [][]cache;

    public boolean isMatch(String s, String p) {
        if(s == null || p == null) return false;
        input = s; pattern = p;
        cache = new int[input.length()][p.length()];
        for(int [] item : cache)
            Arrays.fill(item, -1);
        return isMatch(0, 0);
    }

    public boolean isMatch(int i, int p) {

        if(p == pattern.length()) return i == input.length();
        if(i == input.length()) return isNullable(pattern, p);

        if(cache[i][p] != -1) return cache[i][p] == 1;

        boolean result = false;
        char iCh = input.charAt(i), pCh = pattern.charAt(p);
        boolean hasStar = p < (pattern.length()-1) && pattern.charAt(p+1) == '*';
        HashMap<Integer, Integer> map = new HashMap<>();

        if(hasStar) {
            if(pCh == '.' || pCh == iCh)
                result = isMatch(i+1, p) || isMatch(i+1, p+2) || isMatch(i, p+2);
            else result = isMatch(i, p+2);
        }

        else if(pCh == '.' || iCh == pCh) result = isMatch(i+1, p+1);

        cache[i][p] = result ? 1 : 0;
        return result;
    }

    public boolean isNullable(String pattern, int p) {
        while(p < (pattern.length()-1))
            if(pattern.charAt(p+1) != '*') return false;
            else p += 2;
        return p == pattern.length();
    }
}
