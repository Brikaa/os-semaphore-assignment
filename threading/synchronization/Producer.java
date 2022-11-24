package threading.synchronization;

import threading.jobs.ProductionJob;

public class Producer implements Runnable {
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
