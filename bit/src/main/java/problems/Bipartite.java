package problems;

import java.util.ArrayList;

public class Bipartite {

    final int RED = 1, BLUE = 2;

    class Node {
        public int color = 0;
        public int id;

        public Node(int id) {
            this.id = id;
        }

        public ArrayList<Node> neighbors = new ArrayList<>();
    }

    private Node[] populateGraph(int[][] graph) {
        Node[] nodeArray = new Node[graph.length];
        for (int i = 0; i < graph.length; ++i)
            nodeArray[i] = new Node(i);
        for (int i = 0; i < graph.length; ++i)
            for (int j = 0; j < graph[i].length; ++j)
                nodeArray[i].neighbors.add(nodeArray[j]);
        return nodeArray;
    }

    public boolean isBipartite(int[][] graph) {
        if (graph == null || graph.length <= 1)
            return true;
        Node[] nodeGraph = populateGraph(graph);
        return tryColouring(nodeGraph);
    }

    public Node findUncoloured(Node[] nodeGraph) {
        for (int i = 0; i < nodeGraph.length; ++i)
            if (nodeGraph[i].color == 0)
                return nodeGraph[i];
        return null;
    }

    public boolean tryColouring(Node[] nodeGraph) {
        for (Node start = findUncoloured(nodeGraph); start != null;
             start = findUncoloured(nodeGraph))
            if (!color(start, RED)) return false;
        return true;
    }

    public boolean color(Node node, int color) {
        if (node.color != 0) return node.color == color;
        node.color = color;
        int neighborCol = (color == RED) ? BLUE : RED;
        for (Node neighbor : node.neighbors)
            if (!color(neighbor, neighborCol))
                return false;
        return true;
    }

    public static void main(String[] args) {
        Bipartite bipartite = new Bipartite();
        int[][] graph = {{1, 3}, {0, 2}, {1, 3}, {0, 2}};
        System.out.println(bipartite.isBipartite(graph));
    }
}
