package problems;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class SmallestSufficientTeamTest {

    @Test
    public void shouldReturnSmallestTeam() {
        SmallestSufficientTeam smallestSufficientTeam = new SmallestSufficientTeam();
        String [] req_skills = new String[] { "java", "nodejs", "reactjs" };
        List<List<String>> list = new LinkedList<>();
        list.add(Arrays.asList("java"));
        list.add(Arrays.asList("nodejs"));
        list.add(Arrays.asList("reactjs"));
        int [] output = smallestSufficientTeam.smallestSufficientTeam(req_skills,list);
        assertArrayEquals( new int[]{0,1,2}, output);

        list.add(Arrays.asList("java", "nodejs"));
        output = smallestSufficientTeam.smallestSufficientTeam(req_skills, list);
        assertArrayEquals( new int[]{2,3}, output);
        list.add(Arrays.asList("java", "nodejs", "reactjs"));
        output = smallestSufficientTeam.smallestSufficientTeam(req_skills, list);
        assertArrayEquals( new int[]{4}, output);

        req_skills = new String[] { "java" };
        list.clear();
        list.add(Arrays.asList("java"));
        list.add(Arrays.asList("java"));
        output = smallestSufficientTeam.smallestSufficientTeam(req_skills, list);
        assertEquals(1, output.length);
    }

    @Test
    public void emptyListShouldBeIgnored() {
        SmallestSufficientTeam smallestSufficientTeam = new SmallestSufficientTeam();
        String [] req_skills = new String[] { "java", "nodejs", "reactjs" };
        List<List<String>> list = new LinkedList<>();
        list.add(Arrays.asList());
        list.add(Arrays.asList("java"));
        list.add(Arrays.asList("nodejs"));
        list.add(Arrays.asList("reactjs"));
        list.add(Arrays.asList());
        int [] output = smallestSufficientTeam.smallestSufficientTeam(req_skills,list);
        assertArrayEquals( new int[]{1,2,3}, output);

        req_skills = new String[] {"gvp","jgpzzicdvgxlfix","kqcrfwerywbwi","jzukdzrfgvdbrunw","k"};
        list.clear();
        list.add(Arrays.asList());
        list.add(Arrays.asList());
        list.add(Arrays.asList());
        list.add(Arrays.asList());
        list.add(Arrays.asList("jgpzzicdvgxlfix"));
        list.add(Arrays.asList("jgpzzicdvgxlfix","k"));
        list.add(Arrays.asList("jgpzzicdvgxlfix","kqcrfwerywbwi"));
        list.add(Arrays.asList("gvp"));
        list.add(Arrays.asList("jzukdzrfgvdbrunw"));
        list.add(Arrays.asList("gvp","kqcrfwerywbwi"));
        output = smallestSufficientTeam.smallestSufficientTeam(req_skills,list);
        assertArrayEquals( new int[]{5, 8, 9}, output);
    }
    
    @Test
    public void shouldThrowExceptionIfNoSolution() {
        SmallestSufficientTeam smallestSufficientTeam = new SmallestSufficientTeam();
        String [] req_skills = new String[] { "java", "nodejs", "reactjs" };
        List<List<String>> list = new LinkedList<>();
        list.add(Arrays.asList("java"));
        list.add(Arrays.asList("nodejs"));
        //list.add(Arrays.asList("reactjs"));
        Exception exception = assertThrows(RuntimeException.class, () -> {
            smallestSufficientTeam.smallestSufficientTeam(req_skills, list);
        });
        String expectedMessage = "no solution found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
