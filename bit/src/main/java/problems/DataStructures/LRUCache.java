package problems.DataStructures;

import java.util.HashMap;

class LRUCache {

    final HashMap<Integer, PriorityQueue.Node> hashMap;
    final PriorityQueue priorityQueue;
    final int MAX_CAPACITY;

    int accessTime = 0;

    public LRUCache(int capacity) {
        MAX_CAPACITY = capacity;
        hashMap = new HashMap<>();
        priorityQueue = new PriorityQueue(capacity);
    }

    public int get(int key) {
        if (hashMap.containsKey(key)) {
            PriorityQueue.Node node = hashMap.get(key);
            node.priority = accessTime++;
            priorityQueue.shiftDown(node.index);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {

        if (hashMap.containsKey(key)) {
            PriorityQueue.Node node = hashMap.get(key);
            node.priority = accessTime++;
            priorityQueue.shiftDown(node.index);
            return;
        }
        if (priorityQueue.size() == MAX_CAPACITY) {
            PriorityQueue.Node node = priorityQueue.getNextItem();
            hashMap.remove(node.key);
        }

        PriorityQueue.Node node = priorityQueue.insert(accessTime++, key, value);
        hashMap.put(key, node);
    }
}
