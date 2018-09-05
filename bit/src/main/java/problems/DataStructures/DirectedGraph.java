package problems.DataStructures;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraph<T> extends Graph<T> {

    private final ArrayList<Node> nodeList = new ArrayList<>();

    class DirectedNode<N> extends Node<N> {

        private final List<DirectedNode<N>> neighbours = new ArrayList<>();

        DirectedNode(N data) {
            super(data);
        }

        @Override
        List<DirectedNode<N>> getNeighbours() {
            return neighbours;
        }

        @Override
        boolean connect(Node b) {
            return false;
        }

    }

    @Override
    public Node<T> createNode(T object) {
        return new DirectedNode<T>(object);
    }

    @Override
    public boolean connect(Node<T> a, Node<T> b) {
        return a.connect(b);
    }

    @Override
    public List<Node> getAllItems() {
        return null;
    }
}
