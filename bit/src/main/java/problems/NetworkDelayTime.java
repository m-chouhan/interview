package problems;

import java.util.*;

public class NetworkDelayTime {

    class Edge {
        Node target;
        int cost;
        public Edge(int cost, Node target) {
            this.target = target; this.cost = cost;
        }
    }

    class Node {
        int id;
        int visitCost = Integer.MAX_VALUE;
        public HashSet<Edge> edges = new HashSet<>();
        public Node(int id) { this.id = id; }
    }

    public int networkDelayTime(int[][] times, int N, int K) {

        //setup
        List<Node> nodeList = new ArrayList<>();
        for(int i = 0; i < N; ++i) nodeList.add(new Node(i));

        for(int [] edgeInfo : times) {
            int u = edgeInfo[0]-1, v = edgeInfo[1]-1, w = edgeInfo[2];
            Edge edge = new Edge(w, nodeList.get(v));
            nodeList.get(u).edges.add(edge);
        }

        //dfsFrom(nodeList.get(K-1), 0);
        //bfsFrom(nodeList.get(K-1));
        bfsWithPriority(nodeList.get(K-1));
        int cost = 0;
        for(Node node : nodeList)
            if(node.visitCost == Integer.MAX_VALUE)
                return -1;
            else cost = Math.max(cost, node.visitCost);
        return cost;
    }

    private void dfsFrom(Node node, int visitCost) {
        if(node == null || node.visitCost <= visitCost) return;
        node.visitCost = visitCost;
        for(Edge edge : node.edges)
            dfsFrom(edge.target, node.visitCost + edge.cost);
    }

    private void bfsFrom(Node node) {
        HashSet<Node> visited = new HashSet<>();
        HashSet<Node> unvisited = new HashSet<>();
        node.visitCost = 0;
        unvisited.add(node);

        while(!unvisited.isEmpty()) {
            Node minCostNode = findMinVisitCost(unvisited);
            unvisited.remove(minCostNode);
            visited.add(minCostNode);
            for(Edge edge : minCostNode.edges) {
                Node target = edge.target;
                if(visited.contains(target)) continue;
                target.visitCost = Math.min(minCostNode.visitCost + edge.cost,
                        target.visitCost);
                unvisited.add(edge.target);
            }
        }
    }

    private Node findMinVisitCost(HashSet<Node> unvisited) {
        Node min = null;
        for(Node node : unvisited)
            if(min == null || min.visitCost > node.visitCost)
                min = node;
        return min;
    }

    private void bfsWithPriority(Node node) {
        HashSet<Node> visited = new HashSet<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((a, b) -> a.visitCost - b.visitCost);
        node.visitCost = 0;
        priorityQueue.add(node);
        while(!priorityQueue.isEmpty()) {
            Node min = priorityQueue.remove();
            if(visited.contains(min)) continue;
            visited.add(min);
            min.edges.forEach(edge -> {
                Node target = edge.target;
                if(!visited.contains(target)) {
                    target.visitCost = Math.min(min.visitCost + edge.cost, target.visitCost);
                    priorityQueue.add(target);
                }
            });
        }
    }
}
