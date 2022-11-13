package primewriter.threading;

public class Semaphore {
    private int value;

    public Semaphore(int value) {
        this.value = value;
    }

    public synchronized void tryToAcquire() {
        --value;
        if (value < 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    public synchronized void signal() {
        ++value;
        if (value <= 0) {
            notify();
        }
    }
}
