package primewriter;

class PrimeConsumer implements Runnable {
    private Buffer buffer;

    PrimeConsumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        while (true) {
            buffer.waitForProducedSlot();
            buffer.acquireLock();
            int nextConsumed = buffer.consume();
            buffer.releaseLock();
            buffer.signalConsumed();
            if (nextConsumed == PrimeProducer.DONE_SIGNAL) {
                break;
            }
        }
    }
}
