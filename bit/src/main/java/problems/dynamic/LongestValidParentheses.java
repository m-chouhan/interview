package problems.dynamic;

public class LongestValidParentheses {

    public int longestValidParentheses(String input) {
        if(input == null || input.length() <= 1) return 0;
        int [] maxEndingAt = new int[input.length()];

        int max = 0;

        for(int i = 1; i < maxEndingAt.length; ++i) {

            if(input.charAt(i) == '(') continue;
            switch(input.charAt(i-1)) {
                case '(' :
                    maxEndingAt[i] = i >= 2 ? maxEndingAt[i-2] + 2 : 2;
                    break;
                case ')' :
                    int prevLen = maxEndingAt[i-1];
                    int prevIndex = i - (prevLen+1);
                    maxEndingAt[i] = (prevIndex >= 0 && input.charAt(prevIndex) == '(')
                            ? prevLen + 2 +
                            (prevIndex > 0 ? maxEndingAt[prevIndex-1] : 0)
                            : 0;
                    break;
            }
            max = Math.max(max, maxEndingAt[i]);
        }
        return max;
    }

        
}
