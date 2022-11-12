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
        this.empty = new Semaphore(slots);
        this.lock = new Semaphore(1);
        this.queue = new LinkedList<Integer>();
    }

    public void produce(int number) {
        empty.tryToAcquire();
        lock.tryToAcquire();
        queue.add(number);
        lock.signal();
        full.signal();
    }

    public int consume() {
        full.tryToAcquire();
        lock.tryToAcquire();
        int nextConsumed = queue.remove();
        lock.signal();
        empty.signal();
        return nextConsumed;
    }

}
