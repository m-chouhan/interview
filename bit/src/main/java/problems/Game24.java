package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * https://leetcode.com/problems/24-game/
 */

public class Game24 {

    Double diffThreshold = 0.1;

    public boolean judgePoint24(int[] nums) {
        ArrayList<Double> array = new ArrayList<>();
        for(double num : nums) array.add(num);
        return searchIn(array, 24);
    }

    public ArrayList<Double> getAllPossibleCombi(double A, double B) {
        ArrayList<Double> combinations = new ArrayList<>();
        combinations.add(A + B);
        combinations.add(A - B);
        combinations.add(B - A);
        combinations.add(A * B);
        if(B != 0) combinations.add(A / B);
        if(A != 0) combinations.add(B / A);
        return combinations;
    }

    public boolean searchIn(ArrayList<Double> array, double target) {
        if(array.size() == 1)
            return Math.abs(target - array.get(0)) <= diffThreshold;

        for(int i = 0; i < array.size(); ++i) {
            for(int j = i+1; j < array.size(); ++j) {
                double A = array.get(i), B = array.get(j);
                ArrayList<Double> combinations = getAllPossibleCombi(A, B);
                array.remove(j);
                array.remove(i);
                //ArrayList<Double> nextArray = new ArrayList<>(array);
                for(Double val : combinations) {
                    array.add(val);
                    if(searchIn(array, target))
                        return true;
                    array.remove(array.size()-1);
                }

                array.add(i, A);
                array.add(j, B);
            }
        }

        return false;
    }

    /*
    public boolean judgePoint24(int[] nums) {
        ArrayList<Integer> array = new ArrayList<>();
        for(int num : nums) array.add(num);
        boolean result = searchIn(array, 24);
        return result;
    }

    public HashSet<Double> getAllPossibleCombi(double A, double B) {
        HashSet<Double> combinations = new HashSet<>();
        combinations.add(A + B);
        combinations.add(A - B);
        combinations.add(B - A);
        combinations.add(A * B);
        if(B != 0) combinations.add(A / B);
        if(A != 0) combinations.add(B / A);
        return combinations;
    }

    public void swap(int []array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public boolean searchIn(ArrayList<Integer> array, double target) {
        if(array.size() == 0) return false;
        if(array.size() == 1) {
            boolean result = array.get(0) == target;
            return result;
        }
        if(array.size() == 2) {
            HashSet<Double> possibleValues =
                    getAllPossibleCombi(array.get(0), array.get(1));
            boolean result = possibleValues.contains(target);
            return result;
        }
        for(int i = 0; i < array.size(); ++i) {
            int numA = array.get(i);
            array.remove(i);
            //swap(array, i, length-1);
            HashSet<Double> possibleCombi = getAllPossibleCombi(target, numA);
            for(double newTarget : possibleCombi) {
                HashSet<Double> filterSet = getAllPossibleCombi(newTarget, numA);
                if(filterSet.contains(target) && searchIn(array, newTarget))
                    return true;
            }
            array.add(i, numA);
            //swap(array, i, length-1);
        }
        for(int i = 0; i < array.size(); ++i) {
            for(int j = i+1; j < array.size(); ++j) {
                int numA = array.get(i),numB = array.get(j);
                array.remove(j);
                array.remove(i);
                //swap(array, i, length-1);
                //swap(array, j, length-2);
                HashSet<Double> newNums = getAllPossibleCombi(numA, numB);
                for(double newNum : newNums) {
                    HashSet<Double> possibleCombi = getAllPossibleCombi(target, newNum);
                    for(double newTarget : possibleCombi) {
                        HashSet<Double> filterSet = getAllPossibleCombi(newTarget, newNum);
                        if(filterSet.contains(target) && searchIn(array, newTarget))
                            return true;
                    }
                }

                array.add(i, numA);
                array.add(j, numB);
                //swap(array, i, length-1);
                //swap(array, j, length-2);
            }
        }
        return false;
    }
     */
}
