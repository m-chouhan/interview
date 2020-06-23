package problems;

/* IMPORTANT: Multiple classes and nested static classes are supported */

/*
 * uncomment this if you want to read input.
//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes
import java.util.*;
*/

// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

class MinCostOfOperationInTree {
    public static class Node {
        int id;
        public boolean currentVal;
        public boolean expectedVal;
        public ArrayList<Node> edges = new ArrayList<>();
        Node(int id) {this.id = id;}
        public int minCost(boolean flag) {
            HashSet<Integer> set ;
            if(edges.size() == 0) {
                return flag
                        ? (currentVal == expectedVal ? 0 : 1)
                        : (currentVal == expectedVal ? 1 : 0);
            }
            //change only current node
            int option1 = flag
                    ? (currentVal == expectedVal ? 0 : 1)
                    : (currentVal == expectedVal ? 1 : 0);
            for(Node child : edges) {
                //min cost for changing children to +ve
                option1 += child.minCost(flag);
            }

            //min cost for changing yourself to -ve
            int option2 = flag
                    ? (currentVal == expectedVal ? 1 : 0)
                    : (currentVal == expectedVal ? 0 : 1);
            for(Node child: edges) {
                //min cost for changing childern to -ve
                option2 += child.minCost(!flag);
            }
            //changing all to +ve using 2nd operation
            option2++;
            return Math.min(option1, option2);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        Node [] graph = new Node[n];
        //in.nextLine();
        for(int i = 0; i < n; ++i) graph[i] = new Node(i);
        for(int i_edges=0; i_edges < n-1; i_edges++)
        {
            int u = in.nextInt();
            int v = in.nextInt();
            graph[u-1].edges.add(graph[v-1]);
        }
        for(int i=0; i < n; i++) graph[i].currentVal = (in.nextInt() == 1);
        for(int i=0; i < n; i++) graph[i].expectedVal = (in.nextInt() == 1);
        System.out.println(graph[n-1].minCost(true));
    }
}
