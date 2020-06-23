package problems;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WeekendPartyTest {

    @Test
    public void shouldReturnTrueIfNoCycles() {
        WeekendParty weekendParty = new WeekendParty();
        List<WeekendParty.Node> partyMembers =
                Stream.of(0, 1, 2, 3, 4)
                        .map(integer -> weekendParty.addFriend(integer))
                        .collect(Collectors.toList());
        weekendParty.setHateList(1, 2, 3, 4);
        weekendParty.setHateList(0, 4);
        weekendParty.setHateList(2, 3);
        //weekendParty.printHateList();
        System.out.println(weekendParty.isBiPartyPossible());
        weekendParty.printPartySchedule();
    }
}