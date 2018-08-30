package problems;

import java.util.ArrayList;

public class PrintCombinations {

    static void combine(String instr, StringBuffer outstr, int index, int stack) {
        System.out.println("[" + stack + "] Combine:" + instr + "," + outstr + "," + index);
        for (int i = index; i < instr.length(); i++) {
            outstr.append(instr.charAt(i));
            System.out.println(outstr);
            combine(instr, outstr, i + 1, stack + 1);
            outstr.deleteCharAt(outstr.length() - 1);
        }
    }

    static void permute(String instr, StringBuffer outstr) {

        if (outstr.length() == instr.length()) {
            System.out.println(outstr);
            return;
        }

        for (int i = 0; i < instr.length(); ++i) {
            permute(instr, outstr.append(instr.charAt(i)));
            outstr.deleteCharAt(outstr.length() - 1);
        }
    }

    public static void main(String args[]) {
        combine("123", new StringBuffer(), 0, 0);
        permute("123", new StringBuffer());
    }

    void printConsecutive(ArrayList<Integer> prefix,
                          ArrayList<Integer> suffix) {

        if (suffix.size() < 1) {
            System.out.println(prefix.toString() + suffix.toString());
            return;
        }

        suffix.sort((first, second) -> {
            if (second > first) return 1;
            if (second < first) return -1;
            else return 0;
        });


        for (Integer value : suffix) {

        }
    }
}
