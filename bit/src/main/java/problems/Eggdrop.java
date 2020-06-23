package problems;

import java.security.InvalidParameterException;

public class Eggdrop {

    private static long worstCase(long floor,long availableEggs) {

        if(floor < 0) throw new InvalidParameterException();
        if(floor <= 2) return floor;
        if(availableEggs == 1) return floor;

        long min = floor;
        for(long i = 1;i<=floor;++i) {
            long breaks = worstCase(i-1,availableEggs-1) + 1;
            long notBreaks = worstCase(floor - i,availableEggs) + 1;
            min = Math.min(min,
                    Math.max(breaks,notBreaks));
        }
        return min;
    }

    public static void main(String args[]) {

        System.out.println("wrost case "+ worstCase(10,2));
    }
}
