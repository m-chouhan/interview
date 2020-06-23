package problems;

import java.util.Iterator;
import java.util.LinkedList;

class StreamChecker {

    public static class Node {
        Node[] children;
        boolean isLeaf;
        char value;
        Node(char value) { this.value = value; }
    }

    public static class TrieV2 {
        Node root = new Node('$');

        boolean search(Iterator<Character> it) {
            return search(it, root);
        }

        boolean search(Iterator<Character> it, Node root) {
            if(root == null ) return false;
            else if(!it.hasNext() || root.isLeaf) return root.isLeaf;
            else {
                int index = it.next() - 'a';
                return search(it, root.children[index]);
            }
        }

        boolean search(String word) {
            return search(word, 0, root);
        }

        //search for word in roots 'children' from index
        boolean search(String word, int i, Node root) {
            if(i > word.length() || root == null) return false;
            else if(i == word.length()) return root.isLeaf;
            else if(root.children == null) return false;
            else return search(word, i+1, root.children[word.charAt(i) - 'a']);
        }

        void add(String word) {
            add(word, 0, root);
        }
        //* word is added as 'children' to the root from given index
        void add(String word, int i, Node root) {
            if(i > word.length()) return;
            if(i == word.length()) {
                //nothing more to add i.e root is the last item
                root.isLeaf = true;
                return;
            }
            if(root.children == null) root.children = new Node[26];
            int index = word.charAt(i) - 'a';
            if(root.children[index] == null) root.children[index] = new Node(word.charAt(i));
            add(word, i+1, root.children[index]);
        }
    }

    TrieV2 myTrie = new TrieV2();
    LinkedList<Character> stringList = new LinkedList<Character>();
    public StreamChecker(String[] words) {
        for(String word : words) {
            StringBuilder sb = new StringBuilder(word);
            myTrie.add(sb.reverse().toString());
        }
    }

    public boolean query(char letter) {
        stringList.add(letter);
        return myTrie.search(stringList.descendingIterator());
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */