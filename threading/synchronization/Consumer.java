package threading.synchronization;

import threading.jobs.ConsumptionJob;
import threading.jobs.exceptions.JobException;

public class Consumer implements Runnable {
    private Buffer buffer;
    ConsumptionJob job;

    public Consumer(Buffer buffer, ConsumptionJob jobs) {
        this.buffer = buffer;
        this.job = jobs;
    }

    public void run() {
        try {
            job.initiate();
            while (true) {
                Object nextConsumed = buffer.consume();
                if (nextConsumed.equals(Producer.DONE_SIGNAL)) {
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
