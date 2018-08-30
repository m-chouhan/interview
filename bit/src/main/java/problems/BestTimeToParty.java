package problems;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Comparator;

public class BestTimeToParty {

    protected static int listOfInts[] = {10, 15, 50, 65, 60, 70, 30, 70, 0, 20, 5, 10, 40, 60, 15, 30};

    public static void main(String args[]) {

        ArrayList<Pair<Integer, Integer>> timings = new ArrayList<>();
        for (int i = 0; i < listOfInts.length; i += 2) {
            timings.add(Pair.with(listOfInts[i], listOfInts[i + 1]));
        }
        //timings.sort(Comparator.comparingInt(UniquePair::getValue0));

        ArrayList<Pair<String, Integer>> eventList = new ArrayList<>();
        timings.forEach(pair -> {
            eventList.add(Pair.with("IN", pair.getValue0()));
            eventList.add(Pair.with("OUT", pair.getValue1()));
        });

        eventList.sort(Comparator.comparing(Pair::getValue1));

        int count = 0, maxcount = -1, time = 0;

        for (Pair pair : eventList) {
            if (pair.getValue0().equals("IN")) count++;
            else count--;
            if (maxcount < count) {
                maxcount = count;
                time = (int) pair.getValue1();
            }
            System.out.println(pair.toString() + ',' + count);
        }

        System.out.print("Best time to party is at " + time + " PM");
    }
}
