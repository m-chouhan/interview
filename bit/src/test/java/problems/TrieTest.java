package problems;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TrieTest {

    private Trie INSTANCE;

    @Before
    public void setUp() throws Exception {

        INSTANCE = new Trie();
        List<String> words = Arrays.asList("This", "is", "a", "test");
        words.forEach(word -> INSTANCE.insert(word));
    }

    //later
    @Test
    public void shoudNotBeCaseSensitive() {

    }

    @Test
    public void shouldReturnFalseIfRemoved() {

        INSTANCE.insert("word");
        assert (INSTANCE.search("word"));
        assert (INSTANCE.remove("word"));
        //multiple removal should be false
        assert (!INSTANCE.remove("word"));

        assert (!INSTANCE.search("word"));
        assert (!INSTANCE.search("wo"));

        //what happnens when multiple items are present along same path ?
        assert (INSTANCE.insert("word"));
        assert (INSTANCE.insert("wordy"));

        assert (INSTANCE.remove("word"));
        assert (INSTANCE.search("wordy"));
        assert (!INSTANCE.remove("word"));
        assert (INSTANCE.remove("wordy"));
        assert (!INSTANCE.remove("wordy"));
    }

    @Test
    public void shouldReturnTrueIfExist() {

        String name = "Mahendra";
        INSTANCE.insert(name);

        assert (INSTANCE.search(name));
        assert (!INSTANCE.search("Manendra"));

        INSTANCE.insert("Raju");
        assert (INSTANCE.search("Raju"));
    }

    @Test
    public void shouldReturnFalseOnMultipleAddition() {
        String word = "word";
        assert (INSTANCE.insert(word));
        assert (!INSTANCE.insert(word));
        assert (INSTANCE.insert(word + "someRandomString"));
    }

    @Test
    public void shouldReturnFalseForIntermediateNodes() {

        INSTANCE.insert("Madhuri");
        assert (!INSTANCE.search("Madhu"));
        assert (!INSTANCE.search("Madhuria"));
        assert (INSTANCE.search("Madhuri"));
    }
}