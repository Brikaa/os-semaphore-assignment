package primewriter;

import java.io.FileWriter;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        Buffer buffer = new Buffer(8);
        long max = 150;

        WriteFileJob job = new WriteFileJob("test.txt");
        PrimeProducer primeProducer = new PrimeProducer(buffer, max);
        PrimeConsumer primeConsumer = new PrimeConsumer(buffer, job);
        Thread consumerThread = new Thread(primeConsumer);

        new Thread(primeProducer).start();
        consumerThread.start();
    }
}
