package problems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinCostOfOperationInTreeTest {

    @Test
    public void shouldReturnMinCost() {

        MinCostOfOperationInTree.Node []graph = new MinCostOfOperationInTree.Node[]{
                new MinCostOfOperationInTree.Node(0),
                new MinCostOfOperationInTree.Node(1),
                new MinCostOfOperationInTree.Node(2),
                new MinCostOfOperationInTree.Node(3)};
        graph[0].edges.add(graph[1]);
        graph[0].edges.add(graph[2]);
        graph[0].edges.add(graph[3]);
        int []currentVal = new int[] {0,1,0,1};
        int []expectedVal = new int[] {0,0,0,0};
        for(int i = 0; i < graph.length;++i) {
            graph[i].currentVal = currentVal[i] == 1;
            graph[i].expectedVal = expectedVal[i] == 1;
        }
        assertEquals(2, graph[0].minCost(true));
        assertEquals(2 , graph[0].minCost(false));

        currentVal = new int[] {0,0,0,0};
        expectedVal = new int[] {0,0,0,0};
        for(int i = 0; i < graph.length;++i) {
            graph[i].currentVal = currentVal[i] == 1;
            graph[i].expectedVal = expectedVal[i] == 1;
        }
        assertEquals(0, graph[0].minCost(true));
        assertEquals(1 , graph[0].minCost(false));

        currentVal = new int[] {0,0,0,0};
        expectedVal = new int[] {1,1,1,1};
        for(int i = 0; i < graph.length;++i) {
            graph[i].currentVal = currentVal[i] == 1;
            graph[i].expectedVal = expectedVal[i] == 1;
        }
        assertEquals(1, graph[0].minCost(true));
        assertEquals(0 , graph[0].minCost(false));
    }
}
