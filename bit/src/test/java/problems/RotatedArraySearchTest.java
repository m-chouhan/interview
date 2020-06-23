package problems;

import org.junit.Test;

public class RotatedArraySearchTest {

    @Test
    public void shouldReturnRightStartIndex() {
        RotatedArraySearch search = new RotatedArraySearch();
        int[] input = {1, 2, 3, -1, 0};
        int output = search.findStartIndex(input, 0, input.length - 1);
        assert (output == 3);

        int[] input2 = {6, 1, 2, 3, 4, 5};
        output = search.findStartIndex(input2, 0, input2.length - 1);
        assert (output == 1);

        int[] input3 = {10, 11, 12, 13, 1, 2, 3};
        output = search.findStartIndex(input3, 0, input3.length - 1);
        assert (output == 4);

        int[] input4 = {10, 11, 12, 13};
        output = search.findStartIndex(input4, 0, input4.length - 1);
        assert (output == 0);

        int[] input5 = {10, 11, 12, 13, 1};
        output = search.findStartIndex(input5, 0, input5.length - 1);
        assert (output == 4);
    }

    @Test
    public void bSearchShouldWorkProperly() {

        RotatedArraySearch search = new RotatedArraySearch();
        int[] input = {1, 2, 3, 4, 5};
        search.input = input;
        int output = search.binarySearch(1, 0, input.length - 1);
        assert (output == 0);
        output = search.binarySearch(4, 0, input.length - 1);
        assert (output == 3);
        output = search.binarySearch(6, 0, input.length - 1);
        assert (output == -1);

        int[] input3 = {10, 11, 12, 13, 21, 25, 30};
        search.input = input3;
        output = search.binarySearch(30, 0, input3.length - 1);
        assert (output == 6);

        output = search.binarySearch(0, 0, input3.length - 1);
        assert (output == -1);
    }

    @Test
    public void searchTest() {
        RotatedArraySearch search = new RotatedArraySearch();
        int[] input = {1, 3};
        int output = search.search(input, 3);
        assert (output == 1);

        int[] input2 = {1, 3, 5};
        output = search.search(input2, 3);
        assert (output == 1);

    }
}