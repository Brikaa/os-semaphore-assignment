package primewriter;

import primewriter.threading.*;
import primewriter.jobs.*;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        /*
         * Ideas for GUI:
         * - Create a package, primewriter.gui
         *
         * - Use observer pattern to notify the stopwatch when the consumer is done.
         * - Create a notification class that is passed to the consumer
         * - The stop watch is attached to the notification object
         * - The consumer calls notification.notify()
         * - the notification object should notify the stopwatch (the attached
         * subscriber) to stop
         *
         * - Maybe create a WriteLabelJob to be able to write to both the file and label
         * at the same time
         * - Consumer then receives an array of jobs
         *
         */
        Buffer buffer = new Buffer(8);
        int max = 150;

        ConsumptionJob writeFileJob = new WriteFileJob("test.txt");
        ProductionJob productionJob = new GeneratePrimeJob(max);
        ConsumptionJobList cJobList = new ConsumptionJobList();
        cJobList.addJob(writeFileJob);
        Producer Producer = new Producer(buffer, productionJob);
        Consumer Consumer = new Consumer(buffer, cJobList);

        new Thread(Producer).start();
        new Thread(Consumer).start();
    }
}
