package problems;

import java.util.*;

public class JumpingNumbers {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        while (num > 0) {
            System.out.println("\n**** Printing for " + num + " *****");
            printJumpingNumbers(num);
            num = scanner.nextInt();
        }
        return;
    }

    static void printJumpingNumbers(int num) {
        System.out.print(0);
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= 9; ++i)
            queue.add(i);
        while (!queue.isEmpty()) {
            int current = queue.remove();
            if (current > num) break;
            System.out.print(", " + current);
            int lastDigit = current % 10;
            if (lastDigit > 0)
                queue.add(current * 10 + lastDigit - 1);
            if (lastDigit < 9)
                queue.add(current * 10 + lastDigit + 1);
        }
    }

    static void printJumpingPrefix(int num, int prefix) {
        if (prefix > num) return;
        System.out.println(prefix);

        int lastDigit = prefix % 10;
        if (lastDigit > 0)
            printJumpingPrefix(num, prefix * 10 + (lastDigit - 1));
        if (lastDigit < 9)
            printJumpingPrefix(num, prefix * 10 + (lastDigit + 1));
    }
}