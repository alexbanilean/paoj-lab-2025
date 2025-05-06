package practice;

import java.util.LinkedList;

/**
 * Cerință:
 * Implementați o coadă blocantă (generică) cu metodele put(T item) și take() folosind wait() / notifyAll().
 **/

class MyBlockingQueue<T> {
    private final LinkedList<T> queue = new LinkedList<>();
    private final int capacity;

    public MyBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(T item) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }
        queue.addLast(item);
        notifyAll();
    }

    public synchronized T take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T item = queue.removeFirst();
        notifyAll();
        return item;
    }
}

public class Ex2_BlockingQueue {
    public static void main(String[] args) {
        MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(5);

        Runnable producer = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    queue.put(i);
                    System.out.println("Produced: " + i);
                }
            } catch (InterruptedException e) { e.printStackTrace(); }
        };

        Runnable consumer = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    int item = queue.take();
                    System.out.println("Consumed: " + item);
                }
            } catch (InterruptedException e) { e.printStackTrace(); }
        };

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
