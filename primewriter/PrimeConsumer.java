package primewriter;

class PrimeConsumer implements Runnable {
    private Buffer buffer;
    RunnableAction action;

    PrimeConsumer(Buffer buffer, RunnableAction action) {
        this.buffer = buffer;
        this.action = action;
    }

    public void run() {
        while (true) {
            int nextConsumed = buffer.consume();
            if (nextConsumed == PrimeProducer.DONE_SIGNAL) {
                break;
            }
            action.run(nextConsumed);
        }
    }
}
