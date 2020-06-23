package problems.DataStructures;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraph<T> extends Graph<T> {

    private final ArrayList<DirectedNode<T>> nodeList = new ArrayList<>();

    @Override
    public DirectedNode<T> createNode(T object) {
        int id = nodeList.size();
        DirectedNode<T> node = new DirectedNode<T>(object, id);
        nodeList.add(node);
        return node;
    }

    @Override
    public boolean connect(Node<T> a, Node<T> b) {
        return a.connect(b);
    }

    @Override
    public List<DirectedNode<T>> getAllItems() {
        return nodeList;
    }

    public int createNodeWithId(T object) {
        int id = nodeList.size();
        DirectedNode<T> node = new DirectedNode<T>(object, id);
        nodeList.add(node);
        return id;
    }

    public DirectedNode<T> getNode(int index) {
        return nodeList.get(index);
    }
}
