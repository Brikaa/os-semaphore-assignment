package primewriter;

class PrimeConsumer implements Runnable {
    private Buffer buffer;
    Job job;

    PrimeConsumer(Buffer buffer, Job job) {
        this.buffer = buffer;
        this.job = job;
    }

    public void run() {
        try {
            job.initiate();
            while (true) {
                int nextConsumed = buffer.consume();
                if (nextConsumed == PrimeProducer.DONE_SIGNAL) {
                    break;
                }
                job.run(nextConsumed);
            }
            job.cleanup();
        } catch (JobException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
