package problems;

/**
 * https://leetcode.com/problems/ugly-number-ii/
 */
public class UglyNumber {

    class Pair {
        final int num;
        int index;

        Pair(int num, int index) {
            this.num = num;
            this.index = index;
        }

        int getValue() {
            return num * index;
        }

        void increment() {
            do {
                ++index;
            } while (!isUgly(getValue()));
        }
    }

    Pair getMin(Pair a, Pair b) {
        return a.getValue() < b.getValue() ? a : b;
    }

    public int nthUglyNumber(int n) {
        if (n <= 6) return n;

        Pair two = new Pair(2, 1), three = new Pair(3, 1), five = new Pair(5, 1);

        int previous = 1;
        while (n-- > 1) {
            Pair min = getMin(two, getMin(three, five));
            if (min.getValue() <= previous) {
                n++;
            } else previous = min.getValue();
            min.increment();
        }

        return previous;
    }

    public boolean isUgly(int num) {
        while (num % 2 == 0) num = num / 2;
        while (num % 3 == 0) num = num / 3;
        while (num % 5 == 0) num = num / 5;
        return num == 1;
    }

    public static void main(String args[]) {
        UglyNumber uglyNumber = new UglyNumber();
        System.out.println(uglyNumber.nthUglyNumber(10));
    }
}
