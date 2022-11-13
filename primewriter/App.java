package primewriter;

import primewriter.threading.*;
import primewriter.jobs.*;
import java.io.FileWriter;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        Buffer buffer = new Buffer(8);
        long max = 153;

        Job consumptionJob = new WriteFileJob("test.txt");
        ProductionJob productionJob = new GeneratePrimeJob(max);
        PrimeProducer primeProducer = new PrimeProducer(buffer, productionJob);
        PrimeConsumer primeConsumer = new PrimeConsumer(buffer, consumptionJob);

        new Thread(primeProducer).start();
        new Thread(primeConsumer).start();
    }
}
