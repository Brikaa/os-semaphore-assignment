package primewriter;

public class Semaphore {
    private int value;

    public Semaphore(int value) {
        this.value = value;
    }

    public void try() {
        --value;
        if (value < 0) {
            wait();
        }
    }

    public void signal() {
        ++value;
        if (value <= 0) {
            notify();
        }
    }
}
