package primewriter.jobs;

import java.util.LinkedList;
import java.util.List;

public class ConsumptionJobList implements ConsumptionJob {
    List<ConsumptionJob> jobs;

    public ConsumptionJobList() {
        jobs = new LinkedList<ConsumptionJob>();
    }

    public ConsumptionJobList addJob(ConsumptionJob job) {
        jobs.add(job);
        return this;
    }

    public void initiate() throws JobException {
        for (ConsumptionJob job : jobs) {
            job.initiate();
        }
    }

    public void run(Object message) throws JobException {
        for (ConsumptionJob job : jobs) {
            job.run(message);
        }
    }

    public void cleanup() throws JobException {
        for (ConsumptionJob job : jobs) {
            job.cleanup();
        }
    }
}
