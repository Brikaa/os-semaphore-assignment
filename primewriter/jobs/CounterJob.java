package primewriter.jobs;

public class CounterJob implements ConsumptionJob {
    private int count;

    public CounterJob() {
        this.count = 0;
    }

    public void initiate() {
        count = 0;
    }

    public void run(Object message) {
        count++;
    }

    public void cleanup() {
    }

    public int getCount() {
        return count;
    }
}
