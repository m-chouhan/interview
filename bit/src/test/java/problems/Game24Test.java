package problems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Game24Test {
    @Test
    public void shouldReturnCorrectAnswer() {

        Game24 game24 = new Game24();
        assertEquals(false, game24.judgePoint24(new int[]{}));
        assertEquals(true, game24.judgePoint24(new int[]{1, 9, 1, 2}));
        assertEquals(true, game24.judgePoint24(new int[]{24, 0, 0, 0}));
        assertEquals(false, game24.judgePoint24(new int[]{1, 1, 1, 1}));
        assertEquals(false, game24.judgePoint24(new int[]{2, 2, 2, 2}));
        assertEquals(true, game24.judgePoint24(new int[]{2, 2, 3, 2}));
        assertEquals(true, game24.judgePoint24(new int[]{ 1, 2, 2, 96 }));

    }
}
