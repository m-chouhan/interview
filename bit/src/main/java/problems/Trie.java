package problems;

import java.util.HashMap;

/**
 * TODO: How to gracefully handle exceptions while
 * inserting some items in the array
 */
public class Trie {

    /**
     * @param word: string to remove from DS
     * @return true if removed successfully |
     * false if not found or some error occured => like string does not exists
     */
    public boolean remove(String word) {
        if (root == null) return false;
        return root.remove(word, 0);
    }

    class Node {
        HashMap<Character, Node> childrens = new HashMap<>();
        boolean isTerminalNode = false;
        int count = 0;

        Node() {
        }

        int incrementAndGet() {
            return ++count;
        }

        int decrementAndGet() {
            return --count;
        }

        /**
         * @param word  : word which needs to be inserted
         * @param index : item which should be inserted into current node as children
         * @return true if successfull | false otherwise
         */
        public boolean insert(String word, int index) {
            //invalid state, should never happen
            //throw exception maybe ?
            if (index >= word.length() || index < 0)
                return false;

            Character value = word.charAt(index);
            if (!childrens.containsKey(value)) childrens.put(value, new Node());

            childrens.get(value).incrementAndGet();

            //if last item in the string, mark children as terminal node
            if (index == (word.length() - 1)) {
                childrens.get(value).isTerminalNode = true;
                return true;
            }
            return childrens.get(value).insert(word, index + 1);
        }

        public boolean search(String word, int index) {

            if (index >= word.length() || index < 0) return false;

            Character value = word.charAt(index);
            if (!childrens.containsKey(value)) return false;

            return (index == (word.length() - 1) && childrens.get(value).isTerminalNode) ||
                    childrens.get(value).search(word, index + 1);
        }

        /**
         * we should not try removing any items proactively,
         * we should look for all the nodes which needs to be removed
         * only then iteratively remove items from bottom to top
         *
         * @param word
         * @param index
         * @return false if item doesnot exist, else returns true if removed successfully
         */
        public boolean remove(String word, int index) {

            if (index >= word.length() || index < 0) return false;

            Character value = word.charAt(index);
            if (!childrens.containsKey(value)) return false;
            if (index == (word.length() - 1) && childrens.get(value).isTerminalNode) {
                childrens.get(value).isTerminalNode = false;
                if (childrens.get(value).decrementAndGet() == 0) {
                    childrens.remove(value);
                }
                return true;
            }

            if (childrens.get(value).remove(word, index + 1)) {
                if (childrens.get(value).decrementAndGet() == 0)
                    childrens.remove(value);
                return true;
            }
            return false;
        }
    }

    Node root;

    public boolean insert(String word) {

        if (word.length() == 0) return false;
        if (root == null) {
            root = new Node();
        }
        return root.insert(word, 0);
    }

    /**
     * @param word: string to search for in our DS
     * @return true only if (present||empty) else false
     */
    public boolean search(String word) {
        if (root == null) return false;
        return word.length() == 0 || root.search(word, 0);
    }
}
