package primewriter;

import primewriter.threading.*;
import primewriter.jobs.*;
import java.io.FileWriter;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        /*
            Ideas for GUI:
            - Use observer pattern to notify the stopwatch when the consumer is done.
            - Create a notification class that is passed to the consumer
            - The stop watch is attached to the notification object
            - The consumer calls notification.notify()
            - the notification object should notify the stopwatch (the attached subscriber) to stop

            - Use observer pattern to write to both file and label
            - change WriteFileJob to WriteJob
            - create a Writable class (the subscriber)
            - WriteJob takes an array of Writable
            - WriteJob.run() calls write() in the Writable object
            - Adapter pattern is used to make the file and the label a Writable
            - they would both provide a write() method
        */
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
