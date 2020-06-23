package problems;

import java.util.*;

public class StringTransform {

    boolean lookahead(String input, int index) {
        if (index + 3 >= input.length()) return false;
        for (int j = index + 1; j <= index + 3; ++j)
            if (input.charAt(j) != input.charAt(j - 1) + 1)
                return false;
        return true;
    }

    String transform(String input) {
        int size = input.length();
        String output = "";
        for (int i = 0; i < size; ++i) {
            if (lookahead(input, i)) {
                output += input.charAt(i) + "-" +
                        input.charAt(i + 3);
                i += 3;
            } else output += input.charAt(i);
        }
        return output;
    }

    ArrayList<String> transform(List<String> input) {
        ArrayList<String> output = new ArrayList<>();
        for (String str : input) {
            output.add(transform(str));
        }
        return output;
    }

    public static void main(String args[]) {
        StringTransform stringTransform = new StringTransform();
        stringTransform.transform(Arrays.asList("xyz", "abcd", "asdf", "efghijkl"))
                .forEach(str -> System.out.println(str));
    }
}
