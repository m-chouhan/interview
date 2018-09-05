package problems.DataStructures;

import java.util.List;

public class DirectedGraph<T> extends Graph<T> {

    class DirectedNode<T> extends Node<T> {
        DirectedNode(T data) {
            super(data);
        }

        @Override
        List<T> getNeighbours() {
            return null;
        }

        @Override
        boolean connect(Node<T> b) {
            return false;
        }
    }

    @Override
    public Node<T> createNode(T object) {
        return null;
    }

    @Override
    public boolean connect(Node<T> a, Node<T> b) {
        return a.connect(b);
    }

    @Override
    public List<Node<T>> getAllItems() {
        return null;
    }
}
