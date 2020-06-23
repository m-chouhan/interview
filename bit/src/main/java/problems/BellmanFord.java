package problems;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class BellmanFord {
    static class Edge {
        int source;
        int dst;
        int cost;
        Edge(int source, int dst, int cost) {
            this.source = source; this.dst = dst; this.cost = cost;
        }
    }

    static class Graph {
        private ArrayList<Edge> edges = new ArrayList<>();
        private ArrayList<Integer> nodes = new ArrayList<>();

        ArrayList<Edge> getEdges() {
            edges.clear();
            return edges;
        }
        void addEdge(int source, int dst, int cost) {
            edges.add(new Edge(source, dst, cost));
        }

        Graph(int N) {
            for(int i = 0; i < N; ++i)
                nodes.add(i);
        }
    }

    public int networkDelayTime(int[][] times, int N, int K) {
        Graph graph = new Graph(N);
        for(int [] edge : times)
            graph.addEdge(edge[0]-1, edge[1]-1, edge[2]);

        int[] costArr = bellmanFord(graph, K-1);
        int maxCost = 0;
        for(int cost : costArr) maxCost = Math.max(maxCost, cost);
        return maxCost == Integer.MAX_VALUE ? -1 : maxCost;
    }

    private int[] bellmanFord(Graph graph, int startNode) {
        int [] dstArray = new int[graph.nodes.size()];
        Arrays.fill(dstArray, Integer.MAX_VALUE);
        dstArray[startNode] = 0;
        for(int i = 1; i < dstArray.length; ++i)
            for(Edge edge : graph.getEdges())
                if(dstArray[edge.source] != Integer.MAX_VALUE)
                    dstArray[edge.dst] = Math.min(dstArray[edge.dst], dstArray[edge.source] + edge.cost);
        return dstArray;
    }

}