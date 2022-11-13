package primewriter;

import primewriter.threading.*;
import primewriter.jobs.*;
import java.io.FileWriter;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        Buffer buffer = new Buffer(8);
        int max = 150;

        ConsumptionJob consumptionJob = new WriteFileJob("test.txt");
        ProductionJob productionJob = new GeneratePrimeJob(max);
        Producer Producer = new Producer(buffer, productionJob);
        Consumer Consumer = new Consumer(buffer, consumptionJob);

        new Thread(Producer).start();
        new Thread(Consumer).start();
    }
}
