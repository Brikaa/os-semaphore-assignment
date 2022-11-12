package primewriter;

class PrimeProducer implements Runnable {
    public static int DONE_SIGNAL = -1;
    private long max;
    private Buffer buffer;

    PrimeProducer(Buffer buffer, long max) {
        this.buffer = buffer;
        this.max = max;
    }

    private void produce(int i) {
        buffer.waitForEmptySlot();
        buffer.acquireLock();
        buffer.produce(i);
        buffer.releaseLock();
        buffer.signalProduced();
    }

    public void run() {
        for (int i = 0; i < max; ++i) {
            if (Util.isPrime(i)) {
                produce(i);
            }
        }
        produce(DONE_SIGNAL);
    }
}
