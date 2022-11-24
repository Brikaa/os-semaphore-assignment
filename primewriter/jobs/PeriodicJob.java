package primewriter.jobs;

import threading.jobs.ConsumptionJob;
import threading.jobs.exceptions.JobException;

public class PeriodicJob implements ConsumptionJob {
    private int runPeriod;
    private int runCalls;
    private Object lastMessage;
    private ConsumptionJob job;

    public PeriodicJob(ConsumptionJob job, int runPeriod) {
        this.runPeriod = runPeriod;
        this.runCalls = 0;
        this.job = job;
        this.lastMessage = null;
    }

    public void initiate() throws JobException {
        job.initiate();
    }

    public void run(Object message) throws JobException {
        runCalls %= runPeriod;
        if (runCalls == 0) {
            job.run(message);
        }
        lastMessage = message;
        ++runCalls;
    }

    public void cleanup() throws JobException {
        if (runCalls != 1 && lastMessage != null) {
            job.run(lastMessage);
        }
        job.cleanup();
    }

}
