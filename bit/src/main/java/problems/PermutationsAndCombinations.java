package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Different ways to print permutations and combinations
 */
public class PermutationsAndCombinations {

    static void printAllCombinations(String instr, StringBuffer outstr, int index, int stack) {
        for (int i = index; i < instr.length(); i++) {
            outstr.append(instr.charAt(i));
            System.out.println(outstr);
            printAllCombinations(instr, outstr, i + 1, stack + 1);
            outstr.deleteCharAt(outstr.length() - 1);
        }
    }

    static void iterativeCombinations(String input) {

        int[] array = new int[input.length()];
        while (!allOne(array)) {
            printMask(input, array);
            next(array);
        }
        System.out.println("{" + input + "}");
    }

    static void next(int[] array) {
        int index = 0;
        while (array[index] == 1) {
            array[index] = 0;
            index++;
        }
        if (index < array.length)
            array[index] = 1;

    }

    static boolean allOne(int[] mask) {
        for (int i : mask) if (i == 0) return false;
        return true;
    }

    static void printMask(String str, int[] mask) {
        System.out.print("{");
        for (int i = 0; i < mask.length; ++i) {
            if (mask[i] == 1) System.out.print(str.charAt(i));
        }
        System.out.println("}");
    }

    static void printAllCombinations(String input, String output, int index) {
        if (index >= input.length()) {
            System.out.println("{" + output + "}");
            return;
        }
        printAllCombinations(input, output + input.charAt(index), index + 1);
        printAllCombinations(input, output, index + 1);
    }

    static void permuteWithRepeats(String instr, StringBuffer outstr) {

        if (outstr.length() == instr.length()) {
            System.out.println(outstr);
            return;
        }
        for (int i = 0; i < instr.length(); ++i) {
            permuteWithRepeats(instr, outstr.append(instr.charAt(i)));
            outstr.deleteCharAt(outstr.length() - 1);
        }
    }

    static void permuteWithoutRepeats(StringBuffer instr, StringBuffer outstr, int size) {
        if (outstr.length() == size) {
            System.out.println(outstr);
            return;
        }
        for (int i = 0; i < instr.length(); ++i) {
            char ch = instr.charAt(i);
            if (ch != '*') {
                instr.setCharAt(i, '*');
                outstr.append(ch);
                permuteWithoutRepeats(instr, outstr, size);
                instr.setCharAt(i, ch);
                outstr.deleteCharAt(outstr.length() - 1);
            }
        }
    }

    static void permuteWithoutRepeats2(StringBuffer instr, int index) {
        if (index == instr.length() - 1) {
            System.out.println(instr);
            return;
        }
        for (int swapIndex = index; swapIndex < instr.length(); ++swapIndex) {
            swap(instr, swapIndex, index);
            permuteWithoutRepeats2(instr, index + 1);
            swap(instr, swapIndex, index);
        }
    }

    static void swap(StringBuffer buffer, int index1, int index2) {
        char temp = buffer.charAt(index1);
        buffer.setCharAt(index1, buffer.charAt(index2));
        buffer.setCharAt(index2, temp);
    }

    public static void main(String args[]) {
        iterativeCombinations("123");
        //printAllCombinations("123", new StringBuffer(), 0, 0);
        //printAllCombinations("1234", "", 0);
        //permuteWithRepeats("123", new StringBuffer());
        //permuteWithoutRepeats(new StringBuffer("0123"),new StringBuffer(),2);
        //permuteWithoutRepeats2(new StringBuffer("23"),0);
        //printZeroOne(new StringBuffer(),3);
    }

    static void printZeroOne(StringBuffer outstr, int len) {
        if (outstr.length() == len) {
            System.out.println(outstr);
            return;
        }
        printZeroOne(outstr.append("0"), len);
        outstr.deleteCharAt(outstr.length() - 1);
        printZeroOne(outstr.append("1"), len);
        outstr.deleteCharAt(outstr.length() - 1);
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();

        Stack stack = new Stack<>();
        output.add(stack);
        output.add((List<Integer>) stack.clone());
        return null;
    }
}
