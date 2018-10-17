package problems;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class AlmostSorted {

    // Complete the almostSorted function below.
    static void almostSorted(int[] arr) {
        
        if (arr.length <= 1) {
            System.out.println("no");
            return;
        }
        int start = 0;
        while (start < (arr.length - 1) && arr[start] < arr[start + 1])
            start++;
        if (start == arr.length - 1) {
            System.out.println("no");
            return;
        }
        int end = start;
        while (end < (arr.length - 1) && arr[end] > arr[end + 1])
            end++;

        reverse(arr, start, end);
        if (checkSorted(arr)) {
            System.out.println("yes");
            if ((end - start) == 1)
                System.out.println("swap " + (start + 1) + " " + (end + 1));
            else
                System.out.println("reverse " + (start + 1) + " " + (end + 1));
        } else System.out.println("no");
    }

    private static boolean checkSorted(int[] arr) {
        if (arr.length <= 1) return true;
        for (int i = 0; i < (arr.length - 1); ++i)
            if (arr[i] > arr[i + 1]) return false;
        return true;
    }

    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        almostSorted(arr);

        scanner.close();
    }
}
