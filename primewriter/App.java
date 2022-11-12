package primewriter;

import java.io.FileWriter;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        Buffer buffer = new Buffer(8);
        long max = 100;
        FileWriter fw = new FileWriter("test.txt");

        AfterConsumption action = new AfterConsumption(fw);
        PrimeProducer primeProducer = new PrimeProducer(buffer, max);
        PrimeConsumer primeConsumer = new PrimeConsumer(buffer, action);

        new Thread(primeProducer).start();
        Thread consumerThread = new Thread(primeConsumer);
        consumerThread.start();
        try {
            consumerThread.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("Done");
        fw.close();
    }
}
