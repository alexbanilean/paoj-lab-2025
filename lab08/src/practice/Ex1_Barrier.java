package practice;

/**
 * Cerință:
 * Implementați o barieră de sincronizare pentru N thread-uri fără a folosi CyclicBarrier.
 * Fiecare thread trebuie să aștepte ca toate celelalte fire să ajungă la aceeași barieră înainte să continue.
 **/

class Barrier {
    private final int totalThreads;
    private int waiting = 0;

    public Barrier(int totalThreads) {
        this.totalThreads = totalThreads;
    }

    public synchronized void await() throws InterruptedException {
        waiting++;
        if (waiting < totalThreads) {
            wait();
        } else {
            notifyAll();
        }
    }
}

public class Ex1_Barrier {
    public static void main(String[] args) {
        int N = 5;
        Barrier barrier = new Barrier(N);

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " reached barrier");
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " passed barrier");
        };

        for (int i = 0; i < N; i++) {
            new Thread(task).start();
        }
    }
}
