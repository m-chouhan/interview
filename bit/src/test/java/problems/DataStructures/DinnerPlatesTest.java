package problems.DataStructures;

import org.junit.Test;

import java.util.Stack;

public class DinnerPlatesTest {

    @Test
    public void shouldRetrunItemsInReverse() {
        stackSanity(new DinnerPlatesV2(1));
        stackSanity(new DinnerPlatesV2(2));
        stackSanity(new DinnerPlatesV2(20));
        DinnerPlatesV2 dp = new DinnerPlatesV2(0);
        dp.push(1);
        dp.push(1);
        dp.push(1);
        assert (dp.pop() == -1);
        assert (dp.pop() == -1);
        assert (dp.pop() == -1);
    }

    public void stackSanity(DinnerPlatesV2 dinnerPlates) {
        assert (dinnerPlates.pop() == -1);
        Stack<Integer> testStack = new Stack<>();
        for(int i = 0; i < 100; ++i) {
            dinnerPlates.push(i);
            testStack.push(i);
        }
        for(int i = 0; i < 100; ++i) {
            assert (dinnerPlates.pop() == testStack.pop());
        }
        assert (dinnerPlates.pop() == -1);
        assert (dinnerPlates.pop() == -1);
    }

    @Test
    public void shouldSupportPopAtIndex() {
        DinnerPlatesV2 dp = new DinnerPlatesV2(1);
        popAtIndexSanity(dp);
        assert (dp.size() == 0);
        dp.push(1);
        dp.push(2);
        assert (dp.popAtStack(1) == 2);
        assert (dp.pop() == 1);
        dp.push(5);
        dp.push(3);
        assert (dp.pop() == 3);
        assert (dp.pop() == 5);

        popAtIndexSanity(new DinnerPlatesV2(2));
        popAtIndexSanity(new DinnerPlatesV2(10));
    }

    private void popAtIndexSanity(DinnerPlatesV2 dinnerPlates) {
        for(int i = 0; i < 10; ++i) {
            for(int j = 0; j < dinnerPlates.capacity; ++j)
                dinnerPlates.push(i);
        }
        assert (dinnerPlates.size() == 10);
        assert (dinnerPlates.popAtStack(0) == 0);
        assert (dinnerPlates.popAtStack(10) == -1);
        assert (dinnerPlates.popAtStack(9) == 9);

        dinnerPlates.push(0);
        dinnerPlates.push(9);
        for(int i = 9; i >= 0; --i) {
            for (int j = 0; j < dinnerPlates.capacity; ++j)
                assert (dinnerPlates.pop() == i);
        }

        assert (dinnerPlates.pop() == -1);
        for(int i = 0; i < 10; ++i) {
            for(int j = 0; j < dinnerPlates.capacity; ++j)
                dinnerPlates.push(i);
        }
        for(int i = 0; i < 10; ++i) {
            for (int j = 0; j < dinnerPlates.capacity; ++j)
                assert (dinnerPlates.popAtStack(i) == i);
            assert (dinnerPlates.popAtStack(i) == -1);
        }
        assert (dinnerPlates.pop() == -1);
        assert (dinnerPlates.size() == 0);
        for(int i = 0; i < 10; ++i) assert (dinnerPlates.popAtStack(i) == -1);

    }
}
