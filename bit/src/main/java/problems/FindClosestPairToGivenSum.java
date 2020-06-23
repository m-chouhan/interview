package problems;


import javafx.util.Pair;

public class FindClosestPairToGivenSum {

    Pair<Integer, Integer> findClosest(int [] A, int B[], int target) {

        //sort(A);

        int min = Integer.MAX_VALUE;
        Pair<Integer, Integer> pair = null;

        for(int item : B) {

            int searchVal = target - item;
            int index = findClosest(A, searchVal);
            if(B[index] == searchVal) return new Pair<>(item, B[index]);

            int val1 = Math.abs(target - (B[index] + item));
            int val2 = (index > 0) ? Math.abs(target - (B[index-1] + item)) : Integer.MAX_VALUE;
            if(val1 < min) {
                min = val1;
                pair = new Pair<>(B[index], item);
            }
            if(val2 < min) {
                min = val2;
                pair = new Pair<>(B[index-1], item);
            }
        }
        return pair;
    }

    //returns next largest if no match
    int findClosest(int [] A, int target) {

        int low = 0, high = A.length-1;
        while(low < high-1) {
            int mid = (low + high)/2;
            if(A[mid] == target) return mid;
            if(A[mid] < target) low = mid;
            else high = mid;
        }

        while(A[low] < target && low < A.length) low++;
        return low;
    }
}
