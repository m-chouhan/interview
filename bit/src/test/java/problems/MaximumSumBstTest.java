package problems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaximumSumBstTest {

    public MaximumSumBst.TreeNode generateTreeFromArray(Integer[] array) {

        if(array == null || array[0] == null) return null;

        MaximumSumBst.TreeNode []tree = new MaximumSumBst.TreeNode[array.length];

        for(int i = 0; i < array.length; ++i) {
            if(array[i] == null) continue;
            tree[i] = new MaximumSumBst.TreeNode(array[i]);
            if(i == 0) continue;
            MaximumSumBst.TreeNode parent = tree[(i-1)/2];
            //if left child of parent
            if(i%2 == 1) parent.left = tree[i];
            else parent.right = tree[i];
        }

        return tree[0];
    }

    @Test
    void maxSumBST() {

        MaximumSumBst maximumSumBst = new MaximumSumBst();

        assertEquals(2, maximumSumBst.maxSumBST(
                generateTreeFromArray(new Integer[]{0,1,2})
        ));

        assertEquals(10, maximumSumBst.maxSumBST(
                generateTreeFromArray(new Integer[]{10})
        ));

        assertEquals(3, maximumSumBst.maxSumBST(
                generateTreeFromArray(new Integer[]{5,2,3})
        ));

        assertEquals(3, maximumSumBst.maxSumBST(
                generateTreeFromArray(new Integer[]{5,3,2})
        ));

        assertEquals(13, maximumSumBst.maxSumBST(
                generateTreeFromArray(new Integer[]{5,2,6})
        ));

        assertEquals(20, maximumSumBst.maxSumBST(
                generateTreeFromArray(new Integer[]{1, 4, 3, 2, 4, 2, 5, null, null, null, null, null, null, 4, 6})
        ));
    }
}