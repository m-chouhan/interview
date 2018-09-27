package problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/minimum-height-trees/description/
 * Print all the trees with minium height
 */
public class MinimumHeightTree {

    static class Vertex {
        final int ID;
        final HashSet<Vertex> edges = new HashSet<>();
        boolean visited = false;

        Vertex(int id) {
            this.ID = id;
        }

        void connect(Vertex v) {
            edges.add(v);
        }
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        //graph initialization
        ArrayList<Vertex> vertices = new ArrayList<>();
        for (int i = 0; i < n; ++i) vertices.add(new Vertex(i));
        for (int[] edge : edges) {
            vertices.get(edge[0]).connect(vertices.get(edge[1]));
            vertices.get(edge[1]).connect(vertices.get(edge[0]));
        }

        Set<Vertex> leaves = findLeaves(vertices);
        Set<Vertex> previous = leaves;
        while (leaves.size() > 0) {
            leaves.forEach(vertex -> vertex.visited = true);
            previous = leaves;
            leaves = findNextLeaves(leaves);
        }

        return previous.stream().map(vertex -> vertex.ID).collect(Collectors.toList());
    }


    public Set<Vertex> findLeaves(ArrayList<Vertex> vertices) {
        return vertices
                .stream()
                .filter(this::isLeaf)
                .collect(Collectors.toSet());
    }

    public Set<Vertex> findNextLeaves(Set<Vertex> leaves) {
        return
                leaves
                        .stream()
                        .flatMap(vertex ->
                                vertex.edges.stream().filter(v -> !v.visited))
                        .filter(this::isLeaf)
                        .distinct()
                        .collect(Collectors.toSet());
    }

    public boolean isLeaf(Vertex vertex) {
        return !vertex.visited && (vertex.edges.stream().filter(v -> !v.visited).count() <= 1);
    }

    public static void main(String args[]) {
        MinimumHeightTree instance = new MinimumHeightTree();

        int[][] edges = {{1, 0}, {1, 2}, {1, 3}};
        List<Integer> output = instance.findMinHeightTrees(4, edges);
        output.forEach(integer -> System.out.print(integer + ","));
        System.out.println();

        int[][] edges2 = {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
        output = instance.findMinHeightTrees(6, edges2);
        output.forEach(integer -> System.out.print(integer));
        System.out.println();
        int[][] edges3 = {{0, 1}, {0, 2}};
        output = instance.findMinHeightTrees(3, edges3);
        output.forEach(integer -> System.out.print(integer));
        System.out.println();

        int[][] edges4 = {{0, 1}, {1, 2}, {1, 3}, {2, 4}, {3, 5}, {4, 6}};
        output = instance.findMinHeightTrees(7, edges4);
        output.forEach(integer -> System.out.print(integer));

    }
}
