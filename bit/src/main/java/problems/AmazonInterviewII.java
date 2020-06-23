package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AmazonInterviewII {
    static class StringWrapper {
        String [] parts;
        String original;
        private Pattern digitPattern = Pattern.compile("-?\\d+");
        StringWrapper(String line) {
            original = line;
            parts = line.split(" ", 2);
        }

        public boolean isNumeric(String strNum) {
            if (strNum == null) {
                return false;
            }
            return digitPattern.matcher(strNum).matches();
        }

        boolean hasOnlyNumber() {
            String []parts = original.split(" ");
            for(int i = 1; i < parts.length; ++i) {
                if(!isNumeric(parts[i])) return false;
            }
            return true;
        }

        int compare(StringWrapper stringWrapper) {
            if(parts[1].equals(stringWrapper.parts[1]))
                return parts[0].compareTo(stringWrapper.parts[0]);
            return parts[1].compareTo(stringWrapper.parts[1]);
        }
    }

    public List<String> reorderLines(int logFileSize, List<String> logLines)
    {
        List<StringWrapper> numberList = new ArrayList<>();
        List<StringWrapper> wordsList = new ArrayList<>();
        for(String string : logLines) {
            StringWrapper item = new StringWrapper(string);
            if(item.hasOnlyNumber()) numberList.add(item);
            else wordsList.add(item);
        }
        wordsList.sort(StringWrapper::compare);
        wordsList.addAll(numberList);
        return wordsList.stream().map(item -> item.original).collect(Collectors.toList());
    }

}
