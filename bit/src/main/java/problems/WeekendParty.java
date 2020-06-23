package problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static problems.WeekendParty.Color.BLUE;
import static problems.WeekendParty.Color.RED;

public class WeekendParty {

    final ArrayList<Node> friendsList = new ArrayList<>();

    public void setHateList(int A, int... B) {
        for (int person : B) {
            friendsList.get(A).hates(friendsList.get(person));
        }
    }

    enum Color {RED, BLUE, WHITE}

    class Node {
        int value;
        Color color;
        final HashSet<Node> neighbours = new HashSet<>();

        Node(int value) {
            this.value = value;
            this.color = Color.WHITE;
        }

        Color getColor() {
            return color;
        }

        void setColor(Color color) {
            this.color = color;
        }

        boolean hates(Node node) {
            if (node == this) {
                Logger.getAnonymousLogger().info("You cannot hate yourself");
                return false;
            }
            return neighbours.add(node) && node.hates(this);
        }

        @Override
        public int hashCode() {
            return value;
        }

        @Override
        public String toString() {
            return "[" + value + "," + color + "]";
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node) {
                return ((Node) obj).value == value;
            }
            return false;
        }
    }

    public Node addFriend(int id) {
        Node node = new Node(id);
        friendsList.add(node);
        return node;
    }

    public void printHateList() {
        friendsList.forEach(node -> {
            StringBuffer ouput = new StringBuffer(node.value + " hates ");
            node.neighbours
                    .forEach(hatedNode ->
                            ouput.append(hatedNode.value + " ")
                    );
            Logger.getGlobal().info(ouput.toString());
        });
    }

    public boolean isBiPartyPossible() {
        return BiColouring(friendsList.get(0), RED);
    }

    private boolean BiColouring(Node node, Color color) {

        if (node.getColor() != Color.WHITE)
            return node.getColor() == color;

        node.setColor(color);
        for (Node n : node.neighbours) {
            boolean status = (color == RED) ?
                    BiColouring(n, BLUE) : BiColouring(n, RED);
            if (!status) return false;
        }
        return true;
    }

    public void printPartySchedule() {
        List<Node> redlist = friendsList
                .stream()
                .filter(node -> node.getColor() == RED)
                .collect(Collectors.toList());
        List<Node> bluelist = friendsList
                .stream()
                .filter(node -> node.getColor() == BLUE)
                .collect(Collectors.toList());

        System.out.println("Redlist " + redlist.toString());
        System.out.println("Bluelist " + bluelist.toString());

    }

}
