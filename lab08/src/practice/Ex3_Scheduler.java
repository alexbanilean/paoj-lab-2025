package practice;

import java.util.PriorityQueue;

/**
 * Cerință:
 * Implementați un sistem cu thread-uri de tip worker care preiau task-uri dintr-o coadă cu priorități.
 * Task-urile trebuie executate în ordinea priorității (prioritate mai mare = execuție mai rapidă).
 **/

class Task implements Comparable<Task> {
    private final int priority;
    private final String name;

    public Task(int priority, String name) {
        this.priority = priority;
        this.name = name;
    }

    public void execute() {
        System.out.println(Thread.currentThread().getName() + " executing " + name + " with priority " + priority);
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Task o) {
        return Integer.compare(o.priority, this.priority); // descrescător
    }
}

class TaskScheduler {
    private final PriorityQueue<Task> queue = new PriorityQueue<>();
    private final Object lock = new Object();

    public void submit(Task task) {
        synchronized (lock) {
            queue.add(task);
            lock.notify();
        }
    }

    public void runWorker() {
        new Thread(() -> {
            while (true) {
                Task task = null;
                synchronized (lock) {
                    while (queue.isEmpty()) {
                        try { lock.wait(); } catch (InterruptedException ignored) {}
                    }
                    task = queue.poll();
                    System.out.println(Thread.currentThread().getName() + " picked " + task.getName());
                }
                if (task != null) task.execute();
            }
        }).start();
    }
}

public class Ex3_Scheduler {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();
        scheduler.runWorker();
        scheduler.runWorker();

        scheduler.submit(new Task(1, "Low"));
        scheduler.submit(new Task(3, "High"));
        scheduler.submit(new Task(2, "Medium"));
    }
}

