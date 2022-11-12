package primewriter;

class PrimeProducer implements Runnable {
    // TODO: create a dedicated notification system to also be used in the stopwatch
    public static int DONE_SIGNAL = -1;
    private long max;
    private Buffer buffer;

    PrimeProducer(Buffer buffer, long max) {
        this.buffer = buffer;
        this.max = max;
    }

    public void run() {
        for (int i = 0; i < max; ++i) {
            if (Util.isPrime(i)) {
                buffer.produce(i);
            }
        }
        buffer.produce(DONE_SIGNAL);
    }
}
