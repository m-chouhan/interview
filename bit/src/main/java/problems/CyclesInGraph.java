package problems;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * https://leetcode.com/problems/course-schedule/description/
 */
public class CyclesInGraph {
    static enum State {UNVISITED, VISITING, VISITED}

    static class Vertex {
        State state = State.UNVISITED;
        HashSet<Vertex> dependencies = new HashSet<>();

        void connect(Vertex vertex) {
            dependencies.add(vertex);
        }
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Vertex> vertices = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            vertices.add(new Vertex());
        }

        for (int i = 0; i < prerequisites.length; ++i) {
            vertices
                    .get(prerequisites[i][0])
                    .connect(vertices.get(prerequisites[i][1]));
        }

        for (Vertex unvisited = findUnvisited(vertices);
             unvisited != null;
             unvisited = findUnvisited(vertices)) {
            if (hasCycle(unvisited)) return false;
        }
        return true;
    }

    public boolean hasCycle(Vertex vertex) {
        if (vertex.state == State.VISITING) return true;
        if (vertex.state == State.VISITED) return false;

        vertex.state = State.VISITING;
        for (Vertex v : vertex.dependencies) {
            if (hasCycle(v)) return true;
        }
        vertex.state = State.VISITED;
        return false;
    }

    public Vertex findUnvisited(ArrayList<Vertex> vertices) {
        for (Vertex vertex : vertices)
            if (vertex.state == State.UNVISITED) return vertex;
        return null;
    }

}
