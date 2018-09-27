package problems;

import java.util.*;

/**
 * Given an edge list, print shortest path between any given nodes
 */
public class PrintShortestPaths {

    static class Edge {
        long weight;
        Vertex source, dest;

        Edge(Vertex source, Vertex dest, long weight) {
            this.weight = weight;
            this.source = source;
            this.dest = dest;
        }
    }

    static class Vertex {
        final ArrayList<Edge> edges = new ArrayList<>();
        Vertex prev = null;
        int ID;
        boolean visited;
        long cost;

        Vertex(int id) {
            ID = id;
            visited = false;
            cost = Integer.MAX_VALUE;
        }

        void connect(Vertex vertex, long cost) {
            edges.add(new Edge(this, vertex, cost));
        }
    }

    public static void main(String args[]) {

        Vertex[] vertices = new Vertex[5];
        for (int index = 0; index < vertices.length; ++index) vertices[index] = new Vertex(index);

        vertices[0].connect(vertices[1], 2);
        vertices[0].connect(vertices[2], 1);
        vertices[1].connect(vertices[3], 2);
        vertices[2].connect(vertices[3], 6);
        vertices[3].connect(vertices[4], 4);
        //vertices[4].connect(vertices[0], 2);

        //BFS(vertices.get(0));
        //DFS(vertices[0]);
        //printAllPaths(vertices[0], vertices[3], new Stack<>());
        printShortestPath(vertices[0], vertices[4]);
    }

    public static void BFS(Vertex source) {
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(source);
        while (queue.size() > 0) {
            Vertex vertex = queue.remove();
            if (vertex.visited) continue;
            System.out.println(vertex.ID);
            vertex.visited = true;
            for (Edge edge : vertex.edges)
                if (!edge.dest.visited) queue.add(edge.dest);
        }
    }

    public static void DFS(Vertex source) {
        if (source.visited) return;
        source.visited = true;
        System.out.println(source.ID);

        for (Edge edge : source.edges) {
            DFS(edge.dest);
        }
    }

    public static void printAllPaths(Vertex source, Vertex dest, Stack<Vertex> stack) {
        if (source.visited) return;
        if (source.ID == dest.ID) {
            for (Vertex vertex : stack)
                System.out.print(vertex.ID + " ");
            System.out.println(dest.ID);
            return;
        }
        source.visited = true;
        stack.add(source);
        for (Edge edge : source.edges) {
            printAllPaths(edge.dest, dest, stack);
        }
        source.visited = false;
        stack.pop();
    }

    public static void printShortestPath(Vertex source, Vertex dest) {

        source.cost = 0;
        source.visited = true;
        Set<Edge> activeEdges = new HashSet<>();
        activeEdges.addAll(source.edges);
        Edge current = findMinEdge(activeEdges);
        while (current != null && current.dest != dest) {
            current.dest.visited = true;
            current.dest.prev = current.source;
            current.dest.cost = current.source.cost + current.weight;
            activeEdges.addAll(current.dest.edges);
            current = findMinEdge(activeEdges);
        }

        System.out.print(dest.ID);

        Vertex start = current.source;
        while (start.ID != source.ID) {
            System.out.print(" " + start.ID);
            start = start.prev;
        }

        System.out.print(" " + source.ID);
    }

    public static Edge findMinEdge(Set<Edge> edges) {

        long minCost = Integer.MAX_VALUE;
        Edge minEdge = null;

        for (Edge edge : edges) {
            if (edge.dest.visited) continue;
            long currentCost = edge.source.cost + edge.weight;
            if (currentCost < minCost) {
                minCost = currentCost;
                minEdge = edge;
            }
        }
        return minEdge;
    }
}
