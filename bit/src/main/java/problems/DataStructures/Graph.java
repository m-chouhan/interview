package problems.DataStructures;

import java.util.List;

public abstract class Graph<T> {

    static int OBJECT_COUNT = 0;

    abstract class Node<N> {
        int ID;
        N data;

        Node(N data) {
            this.data = data;
            ID = OBJECT_COUNT++;
        }

        public int getID() {
            return ID;
        }

        public N getData() {
            return data;
        }

        abstract List<? extends Node<N>> getNeighbours();

        abstract boolean connect(Node b);
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
