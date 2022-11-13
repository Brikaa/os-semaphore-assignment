package primewriter.threading;

import primewriter.jobs.ConsumptionJob;
import primewriter.jobs.JobException;

public class Consumer implements Runnable {
    private Buffer buffer;
    ConsumptionJob job;

    public Consumer(Buffer buffer, ConsumptionJob job) {
        this.buffer = buffer;
        this.job = job;
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
