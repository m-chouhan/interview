package problems.dynamic;

public class BTreeMaxPath {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
        }
    }

    int globalMax = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {

        findMaxSinglePathSum(root);
        return globalMax;
    }

    int findMaxSinglePathSum(TreeNode root) {
        if(root == null) return 0;

        int lSum = findMaxSinglePathSum(root.left);
        int rSum = findMaxSinglePathSum(root.right);
        int localMax = Math.max(root.val,
                Math.max(root.val + lSum, root.val + rSum));
        globalMax = Math.max(globalMax,
                Math.max(localMax, root.val + lSum + rSum));
        return localMax;
    }

}
