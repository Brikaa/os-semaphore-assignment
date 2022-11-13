package primewriter.jobs;

public interface ConsumptionJob {
    public void initiate() throws JobException;
    public void run(int number) throws JobException;
    public void cleanup() throws JobException;
}
