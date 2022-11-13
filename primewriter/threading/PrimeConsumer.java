package primewriter.threading;

import primewriter.jobs.Job;
import primewriter.jobs.JobException;

public class PrimeConsumer implements Runnable {
    private Buffer buffer;
    Job job;

    public PrimeConsumer(Buffer buffer, Job job) {
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
