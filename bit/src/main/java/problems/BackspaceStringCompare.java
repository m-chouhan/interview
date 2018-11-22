package problems;

/**
 * [GOOGLE]
 * https://leetcode.com/problems/backspace-string-compare
 */
public class BackspaceStringCompare {
    public int findNextLast(String string, int index) {
        int counter = 0;
        for (int i = index - 1; i >= 0; --i) {
            if (counter > 0) {
                counter = string.charAt(i) == '#' ? counter + 1 : counter - 1;
                continue;
            }
            if (string.charAt(i) != '#') return i;
            counter++;
        }
        return -1;
    }

    public boolean backspaceCompare(String stringA, String stringB) {

        if (stringA == null || stringB == null) return false;

        int indexA = findNextLast(stringA, stringA.length());
        int indexB = findNextLast(stringB, stringB.length());

        while (indexA >= 0 && indexB >= 0) {
            if (stringA.charAt(indexA) != stringB.charAt(indexB))
                return false;
            indexA = findNextLast(stringA, indexA);
            indexB = findNextLast(stringB, indexB);
        }
        if (indexA == -1 && indexB == -1)
            return true;
        return false;
    }
}