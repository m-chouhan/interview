package problems;

import org.junit.Test;
import problems.StreamChecker.TrieV2;

import static org.junit.Assert.assertEquals;

public class StreamCheckerTest {

    @Test
    public void trieV2Test() {
        TrieV2 trieV2 = new TrieV2();
        String array[] = {"my", "name", "is","mahendra","chouhan", "weather", "i", "good", "today", ""};
        for(String str : array) trieV2.add(str);
        for(String str : array)
            assertEquals(true,trieV2.search(str));
        assertEquals(false, trieV2.search("mine"));
        assertEquals(true, trieV2.search(""));
    }

    @Test
    public void shouldCheckForPartialMatches() {
        String [] words = {"hello","friends","how","are","you","x","z"};
        StreamChecker streamChecker = new StreamChecker(words);
        for(String word : words) {
            for(int i = 0; i < word.length(); ++i) {
                boolean exp = i == (word.length() - 1);
                boolean actual = streamChecker.query(word.charAt(i));
                assertEquals(exp, actual);
            }
        }
        assertEquals(streamChecker.query('a'), false);
        assertEquals(streamChecker.query('s'), false);
        assertEquals(streamChecker.query('d'), false);
    }
}
