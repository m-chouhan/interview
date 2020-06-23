package problems;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import problems.BstSequence.TreeNode;

public class BstSequenceTest {

    ArrayList<String> convertToString(List<LinkedList<Integer>> input) {
        ArrayList<String> out = new ArrayList<>();
        for(LinkedList<Integer> list : input) {
            StringBuilder sb = new StringBuilder();
            for(Integer i : list) sb.append(i);
            out.add(sb.toString());
        }
        return out;
    }

    @Test
    public void shouldWeaveItemsProperly() {
        LinkedList<Integer> one = new LinkedList<>(Arrays.asList(1,2,3));
        LinkedList<Integer> two = new LinkedList<>(Arrays.asList(4));
        BstSequence bstSequence = new BstSequence();
        List<LinkedList<Integer>> out = bstSequence.weaveSequences(one,two);
        assertEquals(4, out.size());
        ArrayList<String> convertedOutput = convertToString(out);
        assertEquals(true , convertedOutput.contains("1234"));
        assertEquals(true , convertedOutput.contains("1243"));
        assertEquals(true , convertedOutput.contains("1423"));
        assertEquals(true , convertedOutput.contains("4123"));

        List<LinkedList<Integer>> out2 = bstSequence.weaveSequences(new LinkedList<>(Arrays.asList(1,2)), two);
        assertEquals(3, out2.size());
        convertedOutput = convertToString(out2);
        assertEquals(true , convertedOutput.contains("124"));
        assertEquals(true , convertedOutput.contains("142"));
        assertEquals(true , convertedOutput.contains("412"));

    }

    @Test
    public void shouldReturnAllPermutations() {
        TreeNode node2 = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        node2.left = node1;
        node3.right = node4;
        node2.right = node3;

        BstSequence bstSequence = new BstSequence();

        List<LinkedList<Integer>> out = bstSequence.getSequences(node2);
        assertEquals(3, out.size());
        ArrayList<String> convertedOutput = convertToString(out);
        assertEquals(true, convertedOutput.contains("2134"));
        assertEquals(true, convertedOutput.contains("2314"));
        assertEquals(true, convertedOutput.contains("2341"));

        TreeNode node0 = new TreeNode(0);
        node1.left = node0;
        out = bstSequence.getSequences(node2);
        assertEquals(6, out.size());
        convertedOutput = convertToString(out);
        assertEquals(true, convertedOutput.contains("21034"));
        assertEquals(true, convertedOutput.contains("21304"));
        assertEquals(true, convertedOutput.contains("21340"));
        assertEquals(true, convertedOutput.contains("23104"));
        assertEquals(true, convertedOutput.contains("23140"));
        assertEquals(true, convertedOutput.contains("23410"));


    }
}
