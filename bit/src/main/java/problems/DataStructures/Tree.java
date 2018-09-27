package problems.DataStructures;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.Queue;

public class Tree {

    static class Node {
        Node left;
        Node right;
        long value;

        Node(long value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    Node treefy(int[] array) {
        return treefy(array, 0, array.length - 1);
    }

    //left right are inclusive
    Node treefy(int[] array, int left, int right) {
        if (left > right) throw new InvalidParameterException("left should be less than right!");
        if (left == right) return new Node(array[left], null, null);
        int mid = (left + right) / 2;
        Node leftChild = treefy(array, left, mid);
        Node rightChild = treefy(array, mid + 1, right);
        return new Node(Math.min(leftChild.value, rightChild.value), leftChild, rightChild);
    }

    void print(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (queue.size() > 0) {
            Node n = queue.remove();
            if (n == null) continue;
            System.out.print(n.value + " ");
            if (n.left != null) queue.add(n.left);
            if (n.right != null) queue.add(n.right);
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        Tree tree = new Tree();
        Node node = tree.treefy(array);
        tree.print(node);
    }
}
