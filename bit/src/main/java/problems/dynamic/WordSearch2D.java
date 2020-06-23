package problems.dynamic;

/**
 * https://leetcode.com/problems/word-search/
 * word exist in 2d array or not. *repetations allowed*
 */

public class WordSearch2D {

    public boolean exist(char[][] board, String word) {
        if(word.length() == 0) return true;
        int wLen = word.length();

        int [][][] cache = new int[wLen][board.length][board[0].length];
        for(int length = 1; length <= wLen; ++length)
            for(int i = 0; i < board.length; ++i)
                for(int j = 0; j < board[i].length; ++j) {
                    char ch = word.charAt(wLen - length);
                    int [][] currCache = cache[length-1];
                    int [][] prevCache = length > 1 ? cache[length-2] : null;
                    if(board[i][j] == ch && length == 1)
                        currCache[i][j] = length;
                    else if(board[i][j] == ch && checkNeighbours(prevCache, i, j, length-1))
                        currCache[i][j] = length;
                }

        for(int i = 0; i < board.length; ++i)
            for(int j = 0; j < board[i].length; ++j)
                if(cache[wLen-1][i][j] == wLen) return true;

        return false;
    }

    boolean checkNeighbours(int [][] cache, int i, int j, int searchLen) {
        boolean flag = (i > 0 && cache[i - 1][j] == searchLen);
        flag = flag || (i < cache.length - 1 && cache[i + 1][j] == searchLen);
        flag = flag || (j > 0 && cache[i][j - 1] == searchLen);
        flag = flag || (j < cache[i].length - 1 && cache[i][j + 1] == searchLen);
        return flag;
    }
}
