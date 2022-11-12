package primewriter;
import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    private Semaphore full;
    private Semaphore empty;
    private Semaphore lock;
    private Queue<Integer> queue;

    public Buffer(int slots) {
        this.full = new Semaphore(0);
        this.empty = new Semaphore(this.full);
        this.lock = new Semaphore(1);
        this.queue = new LinkedList<Integer>();
    }

    public void waitForProducedSlot() {
        full.wait();
    }

    public void waitForEmptySlot() {
        empty.wait();
    }

    public void signalProduced() {
        full.signal();
    }

    public void signalConsumed() {
        empty.signal();
    }

    public void acquireLock() {
        lock.wait();
    }

    public void releaseLock() {
        lock.signal();
    }

    public void produce(int number) {
        queue.add(number);
    }

    public int consume(int number) {
        return queue.remove();
    }

}
