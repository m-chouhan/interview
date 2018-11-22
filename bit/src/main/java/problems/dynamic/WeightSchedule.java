package problems.dynamic;

import java.util.ArrayList;
import java.util.List;

public class WeightSchedule {

    static class Schedule {
        public int start;
        public int end;
        public int weight;

        public Schedule(int s, int e, int w) {
            this.start = s;
            this.end = e;
            this.weight = w;
        }
    }

    ;

    public static int findMaxWeight(int[][] schedules) {

        if (schedules == null || schedules.length < 1) return 0;

        ArrayList<Schedule> scheduleList = new ArrayList<>();
        for (int[] s : schedules)
            scheduleList.add(new Schedule(s[0], s[1], s[2]));
        scheduleList.sort((s1, s2) -> s1.end - s2.end);
        int[] maxAt = new int[scheduleList.size()];

        maxAt[0] = scheduleList.get(0).weight;
        for (int i = 1; i < scheduleList.size(); ++i) {
            int disjointIndex = findDisjointOf(i, scheduleList);
            int disjointMax = disjointIndex >= 0 ? maxAt[disjointIndex] : 0;
            maxAt[i] = Math.max(
                    disjointMax + scheduleList.get(i).weight,
                    maxAt[i - 1]
            );
        }

        return maxAt[maxAt.length - 1];
    }

    public static int findDisjointOf(int index, List<Schedule> list) {
        Schedule schedule = list.get(index);
        for (int i = index - 1; i >= 0; --i) {
            if (schedule.start >= list.get(i).end)
                return i;
        }
        return -1;
    }
}
