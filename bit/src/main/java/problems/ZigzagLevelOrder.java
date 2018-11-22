package problems;

import problems.DataStructures.Tree;

import java.util.*;

/**
 * Definition for a binary tree node.
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 */
class ZigzagLevelOrder {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> output = new ArrayList<>();
        if (root == null) return output;

        Queue<TreeNode> queue = new LinkedList<>();
        int level = 0;
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < levelSize; ++i) {
                TreeNode current = queue.remove();
                list.add(current.val);
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
            if (level % 2 == 1) Collections.reverse(list);
            output.add(list);
        }
        return output;
    }

    public List<List<Integer>> zigzagLevelOrderStacks(TreeNode root) {

        List<List<Integer>> output = new ArrayList<>();
        if (root == null) return output;

        Stack<TreeNode>[] stacks = new Stack[2];
        stacks[0] = new Stack<TreeNode>();
        stacks[1] = new Stack<TreeNode>();

        Stack<TreeNode> primary = stacks[0];
        Stack<TreeNode> secondry = stacks[1];
        primary.push(root);
        int level = 0;
        while (!primary.isEmpty() || !secondry.isEmpty()) {
            primary = level % 2 == 0 ? stacks[0] : stacks[1];
            secondry = level % 2 == 0 ? stacks[1] : stacks[0];
            ArrayList<Integer> list = new ArrayList<Integer>();
            while (!primary.isEmpty()) {
                TreeNode current = primary.pop();
                list.add(current.val);
                TreeNode first = (level % 2 == 0) ? current.left : current.right;
                TreeNode second = (level % 2 == 0) ? current.right : current.left;
                if (first != null) secondry.push(first);
                if (second != null) secondry.push(second);
            }
            output.add(list);
            level++;
        }
        return output;
    }
}