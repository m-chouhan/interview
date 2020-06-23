package problems.DataStructures;

/*
    Priority Queue implementation
    #lower value means higher priority
 */
public class PriorityQueue {

    public class Node {
        int priority;
        int value;
        int index;
        int key;

        Node(int priority, int value, int index) {
            this.priority = priority;
            this.value = value;
            this.index = index;
        }
    }

    private Node[] array;
    private final int MAX_CAPACITY;
    private int capacity = 0;

    public PriorityQueue(int capacity) {
        MAX_CAPACITY = capacity;
        array = new Node[MAX_CAPACITY];
    }

    public int size() {
        return capacity;
    }

    public Node peekMin() {
        if (capacity == 0) throw new IndexOutOfBoundsException("No items available in PQ");
        return array[0];
    }

    public Node[] getAllItems() {
        return array;
    }

    public Node insert(int priority, int value) {
        if (capacity >= MAX_CAPACITY) throw new IndexOutOfBoundsException("Queue is already full");

        Node node = new Node(priority, value, capacity);

        array[capacity] = node;
        capacity++;

        int child = capacity - 1;
        int parent = (child - 1) / 2;
        while (array[parent].priority > array[child].priority) {
            swap(parent, child);
            child = parent;
            parent = (child - 1) / 2;
        }
        return node;
    }

    public Node insert(int priority, int key, int value) {
        Node node = insert(priority, value);
        node.key = key;
        return node;
    }

    public void shiftDown(int index) {
        if (index >= capacity || index < 0) return;

        int leftChild = index * 2 + 1;
        int rightChild = index * 2 + 2;

        //already at the bottom, no need to do anything
        if (leftChild >= capacity) return;

        int smallest = leftChild;
        if (rightChild < capacity &&
                array[rightChild].priority < array[leftChild].priority)
            smallest = rightChild;

        if (array[index].priority > array[smallest].priority) {
            swap(index, smallest);
            shiftDown(smallest);
        }
    }

    private void swap(int indexA, int indexB) {
        Node temp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = temp;
        //keeping values in sync
        array[indexA].index = indexA;
        array[indexB].index = indexB;
    }

    public Node getNextItem() {
        if (capacity <= 0) throw new IndexOutOfBoundsException();
        if (capacity == 1) {
            capacity--;
            return array[0];
        }
        swap(0, capacity - 1);
        capacity--;
        shiftDown(0);
        return array[capacity];
    }
}
