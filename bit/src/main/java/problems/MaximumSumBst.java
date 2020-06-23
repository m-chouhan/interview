package problems;

/**
 * https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/
 */
public class MaximumSumBst {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    };

    class TreeInfo {
        public boolean isBst;
        public int sum;
        public int max,min;
        public TreeInfo(boolean isBst, int sum, int max, int min) {
            this.isBst = isBst;
            this.sum = sum;
            this.max = max;
            this.min = min;
        }
        public TreeInfo() {}
    }

    int maxSum = 0;
    public int maxSumBST(TreeNode root) {
        return Math.max(fetchTreeInfo(root).sum, maxSum);
    }

    public TreeInfo fetchTreeInfo(TreeNode root) {

        if(root == null) return new TreeInfo(true, 0, 0, 0);
        if(root.left == null && root.right == null)
            return new TreeInfo(true, root.val, root.val, root.val);

        TreeInfo leftT = fetchTreeInfo(root.left);
        TreeInfo rightT = fetchTreeInfo(root.right);

        maxSum = Math.max(leftT.sum, Math.max(rightT.sum, maxSum));

        boolean isBst = (root.left == null || leftT.max < root.val) &&
                (root.right == null || root.val < rightT.min) &&
                leftT.isBst && rightT.isBst;

        return isBst
                ? new TreeInfo(true, leftT.sum + rightT.sum + root.val,
                root.right == null ? root.val : rightT.max,
                root.left == null ? root.val : leftT.min)
                : new TreeInfo(false, 0, 0, 0);
    }
}
