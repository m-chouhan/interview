package problems.dynamic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrettyPrintTest {

    @Test
    void prettyPrint() {
        PrettyPrint printer = new PrettyPrint();
        List<String> actual = printer.prettyPrint("Geeks for Geeks presents word wrap problem", 15);
        List<String> expected = Arrays.asList("Geeks for Geeks", "presents word", "wrap problem");
        assertEquals(expected, actual);
    }
}