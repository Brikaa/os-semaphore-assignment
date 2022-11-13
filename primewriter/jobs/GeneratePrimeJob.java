package primewriter.jobs;

public class GeneratePrimeJob implements ProductionJob {
    private int maximumNumber;
    private int lastCheckedPrime;
    private boolean primes[];
    private boolean done;
    private static int MAXIMUM_TO_SQUARE = 46340; // floor(sqrt(signed int32 positive limit))

    public GeneratePrimeJob(int maximumNumber) {
        this.maximumNumber = maximumNumber;
        this.cleanup();
    }

    private void updateNextPrimeAndCheckDone() {
        ++lastCheckedPrime;
        while (lastCheckedPrime <= maximumNumber && !primes[lastCheckedPrime]) {
            ++lastCheckedPrime;
        }
        done = lastCheckedPrime > maximumNumber;
    }

    private void fillPrimesUsingLastChecked() {
        if (lastCheckedPrime > MAXIMUM_TO_SQUARE) return;
        for (int i = lastCheckedPrime * lastCheckedPrime; i <= maximumNumber; i += lastCheckedPrime) {
            primes[i] = false;
        }
    }

    public void initiate() {
        primes = new boolean[maximumNumber + 1];
        for (int i = 0; i < maximumNumber; ++i) {
            primes[i] = true;
        }
    }

    public Integer run() {
        int temp = lastCheckedPrime;
        fillPrimesUsingLastChecked();
        updateNextPrimeAndCheckDone();
        return temp;
    }

    public void cleanup() {
        lastCheckedPrime = 2;
        done = false;
    }

    public boolean isDone() {
        return done;
    }
}
