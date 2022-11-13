package primewriter.jobs;

public interface ProductionJob {
    public void initiate();
    public int run();
    public void cleanup();
    public boolean isDone();
}
