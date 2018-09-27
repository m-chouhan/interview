package problems.DataStructures;

import java.util.LinkedList;

public class Heap {

    public class Node {
        Node left, right;
        public int value;

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    private LinkedList<Node> list = new LinkedList<>();

    public Node pop() {

        if (list.size() > 0) {
            swap(list.getFirst(), list.getLast());
            Node item = list.removeLast();
            heapifyFromTop(0);
            return item;
        }
        throw new RuntimeException("Attempt to pop from empty heap!!");
    }

    private void heapifyFromTop(int index) {

        int minChild = getMinChildOf(index);

        if (minChild != -1 && list.get(minChild).value < list.get(index).value) {
            swap(list.get(minChild), list.get(index));
            heapifyFromTop(minChild);
        }
    }

    private int getMinChildOf(int parent) {
        int left = parent * 2 + 1;
        int right = parent * 2 + 2;

        if (left >= list.size()) return -1;
        if (right >= list.size()) return left;

        if (list.get(left).value < list.get(right).value) return left;
        return right;
    }

    public int size() {
        return list.size();
    }

    public void push(Integer item) {
        System.out.println("Pushing " + item + " into queue");
        list.addLast(new Node(item, null, null));
        heapifyFromBottom(list.size() - 1);
    }

    public int getMin() {
        if (list.size() == 0)
            throw new IndexOutOfBoundsException("Attempt to call getMin on an empty array");
        return list.get(0).value;
    }

    private void heapifyFromBottom(int index) {
        int parent = (index - 1) / 2;

        if (list.get(parent).value > list.get(index).value) {
            swap(list.get(parent), list.get(index));
            heapifyFromBottom(parent);
        }
    }

    private void swap(Node A, Node B) {
        int temp = A.value;
        A.value = B.value;
        B.value = temp;
    }
}
