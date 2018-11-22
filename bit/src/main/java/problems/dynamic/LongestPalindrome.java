package problems.dynamic;

/**
 * https://leetcode.com/problems/longest-palindromic-substring
 */

class LongestPalindrome {

    int cache[][];

    public boolean isPalindrome(String string, int i, int j) {
        int N = string.length();
        if (i < 0 || i >= N ||
                j < 0 || j >= N)
            return false;
        if (cache[i][j] != 0)
            return cache[i][j] == 1;
        if (j - i < 2)
            cache[i][j] = string.charAt(i) == string.charAt(j) ? 1 : -1;
        else cache[i][j] = (string.charAt(i) == string.charAt(j) && isPalindrome(string, i + 1, j - 1)) ?
                1 : -1;
        return cache[i][j] == 1;
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;

        final int N = s.length();
        cache = new int[N][N];
        int max = 0, startIndex = -1;

        for (int i = 0; i < N; ++i)
            for (int j = i; j < N; ++j)
                if (isPalindrome(s, i, j) && (j - i + 1) > max) {
                    max = j - i + 1;
                    startIndex = i;
                }
        return s.substring(startIndex, startIndex + max);
    }

    public String longestPalindromeIterative(String string) {
        if (string == null || string.length() <= 1) return string;
        final int N = string.length();
        boolean[][] isPalindrome = new boolean[N][N];
        int max = 1, startIndex = 0;

        for (int j = 0; j < N; ++j) {
            isPalindrome[j][j] = true;
            for (int i = j - 1; i >= 0; --i) {
                isPalindrome[i][j] =
                        string.charAt(i) == string.charAt(j) && (i == j - 1 || isPalindrome[i + 1][j - 1]);
                if (isPalindrome[i][j] && (j - i + 1) > max) {
                    max = j - i + 1;
                    startIndex = i;
                }
            }
        }
        return string.substring(startIndex, startIndex + max);
    }
}