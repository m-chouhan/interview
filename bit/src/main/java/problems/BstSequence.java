package problems;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, print all sequences that will generate the same tree
 * https://discuss.codechef.com/t/given-a-bst-find-all-sequences-of-nodes-that-will-generates-the-same-bst/5644
 */
public class BstSequence {
    public static class TreeNode {
        int value;
        TreeNode left,right;
        TreeNode(int value) { this.value = value; }
    }

    List<LinkedList<Integer>> getSequences(TreeNode root) {
        if(root == null) return null;
        else if(root.left == null && root.right == null) {
            LinkedList<Integer> list = new LinkedList<>();
            list.add(root.value);
            List<LinkedList<Integer>> output = new LinkedList<>();
            output.add(list);
            return output;
        } else if(root.left == null || root.right == null) {
            TreeNode next = root.left == null ? root.right : root.left;
            List<LinkedList<Integer>> output = getSequences(next);
            for(LinkedList<Integer> list : output) list.addFirst(root.value);
            return output;
        }

        List<LinkedList<Integer>> leftSeqs = getSequences(root.left);
        List<LinkedList<Integer>> rightSeqs = getSequences(root.right);
        List<LinkedList<Integer>> output = null;
        for(LinkedList<Integer> leftSeq : leftSeqs)
            for(LinkedList<Integer> rightSeq : rightSeqs) {
                if(output == null)
                    output = weaveSequences(leftSeq, rightSeq);
                else output.addAll(weaveSequences(leftSeq,rightSeq));
        }
        for(LinkedList<Integer> list : output) {
            list.addFirst(root.value);
        }
        return output;
    }

    List<LinkedList<Integer>> weaveSequences(LinkedList<Integer> left, LinkedList<Integer> right) {
        if(left.size() == 0 || right.size() == 0) {
            List<LinkedList<Integer>> output = new LinkedList<>();
            output.add(new LinkedList<>(left.size() == 0 ? right : left));
            return output;
        }
        else if(left.size() == 1 && right.size() == 1) {
            List<LinkedList<Integer>> output = new LinkedList<>();
            LinkedList<Integer> list = new LinkedList<>();
            list.addAll(left);list.addAll(right);
            output.add(new LinkedList<>(list));
            Collections.reverse(list);
            output.add(list);
            return output;
        }

        Integer lfirst = left.removeFirst();
        List<LinkedList<Integer>> output1 = weaveSequences(left, right);
        for(LinkedList<Integer> list : output1) list.addFirst(lfirst);
        left.addFirst(lfirst);

        Integer rfirst = right.removeFirst();
        List<LinkedList<Integer>> output2 = weaveSequences(left, right);
        for(LinkedList<Integer> list : output2) list.addFirst(rfirst);
        right.addFirst(rfirst);
        output1.addAll(output2);
        return output1;
    }
}
