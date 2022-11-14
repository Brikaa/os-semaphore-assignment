package primewriter.jobs;

import primewriter.jobs.exceptions.JobException;

public interface ConsumptionJob {
    public void initiate() throws JobException;
    public void run(Object message) throws JobException;
    public void cleanup() throws JobException;
}
