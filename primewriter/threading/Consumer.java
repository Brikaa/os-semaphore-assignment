package primewriter.threading;

import primewriter.jobs.ConsumptionJobList;
import primewriter.jobs.exceptions.JobException;

public class Consumer implements Runnable {
    private Buffer buffer;
    ConsumptionJobList jobs;

    public Consumer(Buffer buffer, ConsumptionJobList jobs) {
        this.buffer = buffer;
        this.jobs = jobs;
    }

    public void run() {
        try {
            jobs.initiate();
            while (true) {
                Object nextConsumed = buffer.consume();
                if (nextConsumed.equals(Producer.DONE_SIGNAL)) {
                    break;
                }
                jobs.run(nextConsumed);
            }
            jobs.cleanup();
        } catch (JobException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
