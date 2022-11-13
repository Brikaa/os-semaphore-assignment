package primewriter;

public interface Job {
    public void initiate() throws JobException;
    public void run(int number) throws JobException;
    public void cleanup() throws JobException;
}
