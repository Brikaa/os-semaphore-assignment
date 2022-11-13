package primewriter.jobs;

public class GeneratePrimeJob implements ProductionJob {
    private long maximumNumber;
    private int lastCheckedPrime;
    private boolean done;

    public GeneratePrimeJob(long maximumNumber) {
        this.maximumNumber = maximumNumber;
        this.cleanup();
    }

    private boolean isPrime(int number) {
        if (number == 1 || number == 0) {
            return false;
        }
        double root = Math.floor(Math.sqrt(number));
        for (int i = 2; i <= root; ++i) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private void updateNextPrimeAndCheckDone() {
        while (lastCheckedPrime <= maximumNumber && !isPrime(++lastCheckedPrime)) {}
        done = lastCheckedPrime > maximumNumber;
    }

    public void initiate() {
        if (isPrime(lastCheckedPrime)) return;
        updateNextPrimeAndCheckDone();
    }

    public int run() {
        int temp = lastCheckedPrime;
        updateNextPrimeAndCheckDone();
        return temp;
    }

    public void cleanup() {
        lastCheckedPrime = 0;
        done = false;
    }

    public boolean isDone() {
        return done;
    }
}
