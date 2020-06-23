package problems;

import java.util.*;

class WordLadder {

    class Node {
        public int distance = Integer.MAX_VALUE;
        public ArrayList<Node> edges = new ArrayList<>();
        public String data;
        public boolean visited = false;
        public boolean dfsVisit = false;

        Node(String data) {
            this.data = data;
        }
    }

    public List<List<String>> ladderLength(String beginWord,
                                           String endWord,
                                           List<String> wordList) {
        if (wordList == null || wordList.size() == 0) return output;

        HashSet<String> currentLayer = new HashSet<>();

        HashMap<String, Node> graph = new HashMap<>();

        for (String str : wordList) graph.put(str, new Node(str));
        graph.put(beginWord, new Node(beginWord));

        currentLayer.add(beginWord);
        //int distance = 1;

        while (!currentLayer.contains(endWord) && currentLayer.size() > 0) {
            for (String string : currentLayer) graph.get(string).visited = true;

            HashSet<String> nextLayer = new HashSet<>();

            for (String current : currentLayer) {
                Node currentNode = graph.get(current);

                for (int i = 0; i < current.length(); ++i) {
                    char[] charArray = current.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ++ch) {
                        charArray[i] = ch;
                        String generated = new String(charArray);
                        if (graph.containsKey(generated) && !graph.get(generated).visited) {
                            nextLayer.add(generated);
                            graph.get(generated).edges.add(currentNode);
                        }
                    }
                }
            }
            //distance++;
            currentLayer = nextLayer;
        }

        if (currentLayer.size() > 0)
            printAllPaths(graph.get(endWord), graph.get(beginWord), new Stack<>());
        return output;
    }

    List<List<String>> output = new ArrayList<>();

    public void printAllPaths(Node source, Node dest, Stack<String> stack) {
        if (source.dfsVisit) return;

        if (source.data.equals(dest.data)) {
            stack.add(dest.data);
            List<String> path = (List<String>) stack.clone();
            Collections.reverse(path);
            output.add(path);
            stack.pop();
            return;
        }

        source.dfsVisit = true;
        stack.add(source.data);
        for (Node node : source.edges) {
            printAllPaths(node, dest, stack);
        }

        source.dfsVisit = false;
        stack.pop();
    }
}
