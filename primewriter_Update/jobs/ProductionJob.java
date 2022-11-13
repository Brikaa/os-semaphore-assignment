package primewriter.jobs;

public interface ProductionJob {
    public void initiate();
    public Object run();
    public void cleanup();
    public boolean isDone();
}
