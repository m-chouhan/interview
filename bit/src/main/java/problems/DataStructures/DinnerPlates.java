/**
 * https://leetcode.com/problems/dinner-plate-stacks/
 */

package problems.DataStructures;

class DinnerPlates {

    class DinnerStackException extends RuntimeException {
        public DinnerStackException(String s) {
            super(s);
        }
    }

    class Stack<T> {
        public class Node {
            T val;
            Node next, prev;
            public Node(T val) { this.val = val; next = null; prev = null; }
        }

        private Node head, tail;
        private int size = 0;
        private final int capacity;

        void push(T val) throws DinnerStackException {
            if(size == capacity) throw new DinnerStackException("Stack Full");

            Node temp = new Node(val);
            temp.next = head;
            if(head != null) head.prev = temp;
            else tail = temp;
            head = temp;
            size++;
        }

        T pop() {
            if(size == 0) throw new DinnerStackException("Empty Stack");
            T result = head.val;
            head = head.next;
            if(head == null) tail = null;
            else head.prev = null;
            size--;
            return result;
        }

        public Stack(int capacity) { this.capacity = capacity; }
        public int size() { return size; }
        public T peek()  {
            return peekNode().val;
        }
        public Node peekNode() {
            if(size == 0) throw new DinnerStackException("Empty Stack");
            return head;
        }
        public boolean isEmpty() { return size == 0; }
        public boolean isFull() { return size == capacity; }
        //seek from bottom of stack
        public T seek(int index) {
            return seekNode(index).val;
        }

        public Node seekNode(int index) {
            if(isEmpty()) throw new DinnerStackException("Empty Stack");
            if(index >= size) throw new DinnerStackException("Out of bounds");
            Node ptr = tail;
            while(index > 0) {
                ptr = ptr.prev;
                index--;
            }
            return ptr;
        }
    }

    //////////////Dinner Plates logic
    final int capacity;
    final Stack<Stack<Integer>> Stack;

    Stack<Stack<Integer>>.Node firstEmptyStack;
    int firstEmptyStackIndex = 0;

    public DinnerPlates(int capacity) {
        this.capacity = capacity;
        this.Stack = new Stack<>(Integer.MAX_VALUE);
    }

    public int size() { return Stack.size(); }
    public void push(int val) {
        try {
            Stack<Integer> st = null;
            if(Stack.isEmpty()) {
                st = new Stack<Integer>(capacity);
                Stack.push(st);
                firstEmptyStackIndex = 0;
                firstEmptyStack = Stack.seekNode(0);
            } else if(firstEmptyStack.val.isFull()) {
                while(firstEmptyStack.prev != null && firstEmptyStack.val.isFull()) {
                    firstEmptyStack = firstEmptyStack.prev;
                    firstEmptyStackIndex++;
                }
                if(firstEmptyStack.val.isFull()) {
                    st = new Stack<Integer>(capacity);
                    Stack.push(st);
                    firstEmptyStackIndex = Stack.size() - 1;
                    firstEmptyStack = Stack.peekNode();
                }
            }
            st = firstEmptyStack.val;
            st.push(val);
        } catch (DinnerStackException ds) {}
    }

    public int pop() {
        try {
            while(Stack.peek().isEmpty()) {
                Stack.pop();
            }
            int val = Stack.peek().pop();
            if(Stack.isEmpty()) {
                firstEmptyStack = null;
                firstEmptyStackIndex = -1;
            }
            else if((Stack.size() - 1) < firstEmptyStackIndex) {
                firstEmptyStack = Stack.peekNode();
                firstEmptyStackIndex = Stack.size() - 1;
            }
            return val;
        } catch (DinnerStackException ds) {
            return -1;
        }
    }

    public int popAtStack(int index) {
        try {
            Stack<Integer> st;
            if(index < firstEmptyStackIndex) {
                firstEmptyStack = Stack.seekNode(index);
                st = firstEmptyStack.val;
                firstEmptyStackIndex = index;
            } else st = Stack.seek(index);
            return st.pop();
        } catch (DinnerStackException ds) {
            return -1;
        }
    }
}

/**
 * Your DinnerPlates object will be instantiated and called as such:
 * DinnerPlates obj = new DinnerPlates(capacity);
 * obj.push(val);
 * int param_2 = obj.pop();
 * int param_3 = obj.popAtStack(index);
 */