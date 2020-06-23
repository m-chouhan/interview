package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordJustify {
    private String [] words;
    private int maxWidth;

    public List<String> fullJustify(String[] words, int maxWidth) {

        ArrayList<String> answer = new ArrayList<>();
        if(words == null) return answer;

        this.words = words; this.maxWidth = maxWidth;

        int startIndex = 0;
        int endIndex = findNextWords(startIndex);
        while(endIndex < words.length-1) {
            String justified = centerJustify(startIndex, endIndex);
            answer.add(justified);
            startIndex = endIndex+1;
            endIndex = findNextWords(startIndex);
        }

        StringBuilder builder = new StringBuilder();
        int lineLength = 0;
        for(int i = startIndex; i < endIndex; ++i) {

            builder.append(words[i]).append(" ");
            lineLength += words[i].length() + 1;
        }
        lineLength += words[endIndex].length();
        int lastLineSpaces = maxWidth - lineLength;
        builder.append(words[endIndex]);
        while(lastLineSpaces-- > 0) builder.append(" ");
        answer.add(builder.toString());
        return answer;
    }

    private int findNextWords(int startIndex) {
        int currentLength = 0;
        for(int i = startIndex; i < words.length; ++i) {
            currentLength += words[i].length();
            if(currentLength == maxWidth) return i;
            else if(currentLength < maxWidth) currentLength++;
            else return i-1;
        }
        return words.length - 1;
    }

    private String centerJustify(int startIndex, int endIndex) {

        if(startIndex == endIndex) {
            StringBuilder builder = new StringBuilder();
            builder.append(words[startIndex]);
            int spaceLen = maxWidth - words[startIndex].length();
            while(spaceLen-- > 0) builder.append(" ");
            return builder.toString();
        }

        int wordsLength = 0;
        for(int i = startIndex; i <= endIndex; ++i)
            wordsLength += words[i].length();
        int mandatorySpace = endIndex - startIndex;
        int extraSpace = maxWidth - (wordsLength + mandatorySpace);
        String [] finalSpaces = new String[mandatorySpace];
        Arrays.fill(finalSpaces, " ");
        for(int i = 0; i < extraSpace; ++i)
            finalSpaces[i%finalSpaces.length] += " ";

        StringBuilder builder = new StringBuilder();
        for(int i = startIndex; i < endIndex; ++i) {
            builder
                    .append(words[i])
                    .append(finalSpaces[i-startIndex]);
        }
        builder.append(words[endIndex]);
        return builder.toString();
    }
}
