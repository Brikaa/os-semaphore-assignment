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

    public void produce(int number) {
        empty.try();
        lock.try();
        queue.add(i);
        lock.signal();
        full.signal();
    }

    public int consume(int number) {
        full.try();
        lock.try();
        int nextConsumed = queue.remove();
        lock.signal();
        empty.signal();
        return nextConsumed;
    }

}
