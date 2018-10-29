package problems.vmware;

import java.util.*;

public class ProducerConsumer {

    static Queue<Integer> queue = new LinkedList<>();
    static final int MAX_SIZE = 10;

    static Object lockproducer, lockConsumers, readConsumeLock;
    static Set<Integer> set = new HashSet<>();

    static class Producer {

        void add(int value) {

            queue.remove();
            queue.add(1);
            while (queue.size() >= MAX_SIZE) {
                try {
                    lockproducer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.offer(value);
            lockConsumers.notifyAll();
            //queue.notify();
        }
    }

    static class Consumer {

        Integer peek() {
            return queue.peek();
        }

        synchronized Integer read() {

            while (queue.size() == 0) {
                //lockConsumers.wait();
            }

            Integer value = queue.poll();

            lockproducer.notifyAll();
            return value;
        }
    }

    public static void main(String[] arg) {

        Producer producer = new Producer();

        ArrayList<Consumer> consumers = new ArrayList<>();


    }

}
