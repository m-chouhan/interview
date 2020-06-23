package problems;

import java.util.*;

public class CloneGraph {
    /**
     * https://leetcode.com/problems/clone-graph/
     * Definition for undirected graph.
     */
    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    }
    
    HashMap<Integer, UndirectedGraphNode> genNodes = new HashMap<>();
    HashSet<Integer> visited = new HashSet<>();

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return copyBFS(node);
    }

    UndirectedGraphNode copyBFS(UndirectedGraphNode node) {
        if (node == null) return null;

        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        UndirectedGraphNode start = getNode(node.label);
        queue.add(node);
        while (!queue.isEmpty()) {
            UndirectedGraphNode current = queue.remove();
            if (visited.contains(current.label)) continue;

            UndirectedGraphNode genNode = getNode(current.label);
            for (UndirectedGraphNode neighbor : current.neighbors) {
                genNode.neighbors.add(getNode(neighbor.label));
                queue.add(neighbor);
            }
            visited.add(genNode.label);
        }
        return start;
    }

    UndirectedGraphNode copyDFS(UndirectedGraphNode node) {
        if (node == null) return null;
        else if (visited.contains(node.label)) return genNodes.get(node.label);

        UndirectedGraphNode clone = getNode(node.label);
        for (UndirectedGraphNode neighbor : node.neighbors) {
            clone.neighbors.add(getNode(neighbor.label));
        }
        visited.add(clone.label);

        for (UndirectedGraphNode neighbor : node.neighbors) {
            copyDFS(neighbor);
        }
        return clone;
    }

    UndirectedGraphNode getNode(int label) {
        if (!genNodes.containsKey(label))
            genNodes.put(label, new UndirectedGraphNode(label));
        return genNodes.get(label);
    }

}
