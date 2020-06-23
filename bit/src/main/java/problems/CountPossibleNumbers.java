package problems;

public class CountPossibleNumbers {

    public void printNumbers(int M, int N) {
        printNumbers(M, N, new StringBuffer());
    }

    private void printNumbers(int M, int N, StringBuffer outstr) {
        if (outstr.length() == M) {
            System.out.println(outstr);
            return;
        }
        int value = outstr.length() > 0 ? 2 * Character.getNumericValue(outstr.charAt(outstr.length() - 1)) : 1;
        for (int i = value; i <= N; ++i) {
            outstr.append("" + i);
            printNumbers(M, N, outstr);
            outstr.deleteCharAt(outstr.length() - 1);
        }
    }

    public static void main(String args[]) {
        CountPossibleNumbers instance = new CountPossibleNumbers();
        instance.printNumbers(2, 9);
    }
}
