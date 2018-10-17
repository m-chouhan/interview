package problems;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class TopologicalSort {

    enum State {VISITED, UNVISITED, VISITING}

    public static class Vertex {
        private final int id;
        State state = State.UNVISITED;
        HashSet<Vertex> dependencies = new HashSet<>();

        Vertex(int id) {
            this.id = id;
        }

        void connect(Vertex vertex) {
            dependencies.add(vertex);
        }
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<Vertex> vertices = new ArrayList<>();
        Stack<Vertex> stack = new Stack<>();
        for (int i = 0; i < numCourses; ++i) {
            vertices.add(new Vertex(i));
        }

        for (int i = 0; i < prerequisites.length; ++i) {
            vertices
                    .get(prerequisites[i][0])
                    .connect(vertices.get(prerequisites[i][1]));
        }

        for (Vertex vertex = findUnvisited(vertices); vertex != null; vertex = findUnvisited(vertices)) {
            if (!sort(vertex, stack)) return new int[0];
        }

        int[] output = new int[stack.size()];
        while (!stack.isEmpty()) {
            int id = stack.pop().id;
            output[stack.size()] = id;
        }
        return output;
    }

    Vertex findUnvisited(List<Vertex> vertices) {
        for (Vertex vertex : vertices)
            if (vertex.state == State.UNVISITED) return vertex;
        return null;
    }

    boolean sort(Vertex vertex, Stack<Vertex> stack) {
        if (vertex.state == State.VISITING) return false;
        if (vertex.state == State.VISITED) return true;

        vertex.state = State.VISITING;

        for (Vertex neighbour : vertex.dependencies) {
            if (!sort(neighbour, stack)) return false;
        }

        vertex.state = State.VISITED;
        stack.push(vertex);
        return true;
    }
}
