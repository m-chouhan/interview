/**
 * https://leetcode.com/problems/dinner-plate-stacks/
 */

package problems.DataStructures;

import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeSet;

class DinnerPlatesV2 {

    final int capacity;
    int size = 0;
    ArrayList<Stack<Integer>> list;
    TreeSet<Integer> emptyIndexList;
    public DinnerPlatesV2(int capacity) {
        this.capacity = capacity;
        list = new ArrayList<>();
        emptyIndexList = new TreeSet<>();
    }

    public int size() { return size; }
    public void push(int val) {
        if(capacity == 0) return;
        if(emptyIndexList.isEmpty()) {
            if(size < list.size()) {
                size++;
                emptyIndexList.add(size - 1);
            } else {
                list.add(new Stack<>());
                emptyIndexList.add(list.size() - 1);
                size++;
            }
        }
        Stack<Integer> st = list.get(emptyIndexList.first());
        st.add(val);
        if(st.size() == capacity) {
            emptyIndexList.pollFirst();
        }
    }

    public int pop() {
        while(size > 0 && list.get(size - 1).empty()) {
            size--;
            emptyIndexList.remove(size);
        }
        if(size == 0) return -1;
        Stack<Integer> st = list.get(size - 1);
        emptyIndexList.add(size - 1);
        return st.pop();
    }

    public int popAtStack(int index) {
        if(index >= size) return -1;
        Stack<Integer> st = list.get(index);
        emptyIndexList.add(index);
        return st.empty() ? -1 : st.pop();
    }
}
