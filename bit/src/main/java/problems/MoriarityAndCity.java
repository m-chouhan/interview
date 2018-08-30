package problems;

import java.util.Scanner;

public class MoriarityAndCity {

    public static void main(String args[]) throws Exception {

        Scanner input$ = new Scanner(System.in);
        long N = input$.nextLong();

        if (N == 0) {
            System.out.println("0");
            return;
        }
        long maxBlockSize = 1;
        long startIndex = 0;
        int current = input$.nextInt();

        for (long i = 1; i < N; ++i) {
            int value = input$.nextInt();
            if (value != current) {
                long currentBlockSize = i - startIndex;
                if (currentBlockSize > maxBlockSize)
                    maxBlockSize = currentBlockSize;
                startIndex = i;
                current = value;
            }
        }

        System.out.println(maxBlockSize);
    }
}
