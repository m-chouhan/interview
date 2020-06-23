package problems.google;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;

/**
 * https://leetcode.com/problems/count-complete-tree-nodes/
 */
public class BinarySearch {

    //assume
    class TreeNode {
        int value;
        TreeNode left, right;
    }

    boolean findIndex(TreeNode root, int index) {
        ArrayList<Integer> path = getPathFromIndex(index);
        if(path.size() == 1) return root != null;
        String string;
        return traversePath(root, path, 1);
    }

    ArrayList<Integer> getPathFromIndex(int index) {
        ArrayList<Integer> list = new ArrayList<>();
        while(index > 0) {
            list.add(index);
            index = index/2;
        }
        Collections.reverse(list);
        return list;
    }

    boolean traversePath(TreeNode root, ArrayList<Integer> path, int index) {
        if(root == null) return false;

        int value = path.get(index);
        if(index == path.size() - 1)
            return (value%2 == 0) ? root.left != null : root.right != null;

        return (value%2 == 0)
                ? traversePath(root.left, path, index+1)
                : traversePath(root.right, path, index+1);
    }

    public int countNodes(TreeNode root) {
        int height = 0;
        TreeNode node = root;
        while(node != null) {
            node = node.left;
            height++;
        }

        if(height <= 1) return height;

        int rightMost = (int) (Math.pow(2, height) - 1);
        int leftMost = (int) (Math.pow(2, height-1));
        return binarySearch(leftMost, rightMost, root);
    }

    int binarySearch(int l, int r, TreeNode root) {

        while(l < r-1) {
            int mid = (l+r)/2;
            if(findIndex(root, mid)) l = mid;
            else r = mid;
        }

        return findIndex(root, r) ? r : l;
    }
}
