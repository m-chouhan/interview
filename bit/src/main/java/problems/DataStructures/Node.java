package problems.DataStructures;

import java.util.List;

abstract class Node<T> {

    final int ID;
    T data;

    Node(T data, int id) {
        ID = id;
        this.data = data;
    }

    public int getID() {
        return ID;
    }

    public T getData() {
        return data;
    }

    @Override
    public int hashCode() {
        return ID;
    }

    abstract List<? extends Node<T>> getNeighbours();

    abstract boolean connect(Node<T> b);

}
