package primewriter.threading;
import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    private Semaphore full;
    private Semaphore empty;
    private Semaphore lock;
    private Queue<Object> queue;

    public Buffer(int slots) {
        this.full = new Semaphore(0);
        this.empty = new Semaphore(slots);
        this.lock = new Semaphore(1);
        this.queue = new LinkedList<Object>();
    }

    public void produce(Object message) {
        empty.tryToAcquire();
        lock.tryToAcquire();
        queue.add(message);
        lock.signal();
        full.signal();
    }

    public Object consume() {
        full.tryToAcquire();
        lock.tryToAcquire();
        Object nextConsumed = queue.remove();
        lock.signal();
        empty.signal();
        return nextConsumed;
    }

}
