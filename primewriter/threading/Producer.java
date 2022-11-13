package primewriter.threading;

import primewriter.jobs.ProductionJob;

public class Producer implements Runnable {
    // TODO: create a dedicated notification system to also be used in the stopwatch
    public static int DONE_SIGNAL = -1;
    private ProductionJob job;
    private Buffer buffer;

    public Producer(Buffer buffer, ProductionJob job) {
        this.buffer = buffer;
        this.job = job;
    }

    public void run() {
        job.initiate();
        while (!job.isDone()) {
            buffer.produce(job.run());
        }
        buffer.produce(DONE_SIGNAL);
        job.cleanup();
    }
}
