package primewriter.jobs;

//import java.util.List;
import primewriter.threading.*;

public class Intialize implements Command {

    private Buffer buffer;
    private Producer producer;
    private Consumer consumer;
    private ProductionJob GeneratePrime;
    private ConsumptionJobList list;

    public Intialize(int maximumNumber, int size, ConsumptionJobList List) {
        buffer = new Buffer(size);
        list = List;
        GeneratePrime = new GeneratePrimeJob(maximumNumber);
        producer = new Producer(buffer, GeneratePrime);
        consumer = new Consumer(buffer, list);

    }

    // for reusability
    public void setMaxNumber(int maximumNumber) {
        GeneratePrime = new GeneratePrimeJob(maximumNumber);
        producer = new Producer(buffer, GeneratePrime);
    }

    public void setBuffer(int size) {
        buffer = new Buffer(size);
    }

    public void execute() {
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
