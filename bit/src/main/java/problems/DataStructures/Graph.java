package problems.DataStructures;

import java.util.List;

public abstract class Graph<T> {

    static int OBJECT_COUNT = 0;

    abstract class Node<T> {
        int ID;
        T data;

        Node(T data) {
            this.data = data;
            ID = OBJECT_COUNT++;
        }

        public int getID() {
            return ID;
        }

        public T getData() {
            return data;
        }

        abstract List<T> getNeighbours();

        abstract boolean connect(Node<T> b);
    }

    public abstract Node<T> createNode(T object);

    /**
     * Connects two nodes together
     *
     * @param a : first node
     * @param b : second node
     * @return true if connection success | false if already exists
     */
    public abstract boolean connect(Node<T> a, Node<T> b);

    public abstract List<Node<T>> getAllItems();

}
