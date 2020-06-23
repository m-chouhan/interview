package problems.DataStructures;

import java.util.ArrayList;
import java.util.List;

public class DirectedNode<T> extends Node<T> {

    private final ArrayList<DirectedNode<T>> neighbours = new ArrayList<>();

    DirectedNode(T data, int id) {
        super(data, id);
    }

    @Override
    public List<DirectedNode<T>> getNeighbours() {
        return neighbours;
    }

    //TODO: how to do this more strictly
    @Override
    public boolean connect(Node<T> b) {
        DirectedNode B = (DirectedNode) b;
        return !neighbours.contains(B) && neighbours.add(B);
    }
}
