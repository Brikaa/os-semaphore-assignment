package primewriter;

import primewriter.threading.*;
import primewriter.gui.*;
import primewriter.jobs.*;
import java.io.FileWriter;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        /*
            Ideas for GUI:
            - Create a package, primewriter.gui

            - Use observer pattern to notify the stopwatch when the consumer is done.
            - Create a notification class that is passed to the consumer
            - The stop watch is attached to the notification object
            - The consumer calls notification.notify()
            - the notification object should notify the stopwatch (the attached subscriber) to stop

            - Maybe create a WriteLabelJob to be able to write to both the file and label at the same time
            - Consumer then receives an array of jobs

        */
        Buffer buffer = new Buffer(8);
        int max = 150;
        
        ConsumptionJob[] array = new ConsumptionJob [2];
        // attaching the stop watch
        StopWatch stopwatch = new StopWatch();
        Notification notification = new Notification();
        notification.register(stopwatch);

        // intializing jobs
        // can we use a factory pattern here ?
        ConsumptionJob consumptionJob = new WriteFileJob("test.txt");
        ConsumptionJob writeLabel = new WriteLabelJob();
        array[0] = consumptionJob;
        array[1] = writeLabel;
        ProductionJob productionJob = new GeneratePrimeJob(max);
        
        Producer Producer = new Producer(buffer, productionJob);
        Consumer Consumer = new Consumer(buffer, array, notification);

        new Thread(Producer).start();
        new Thread(Consumer).start();
    }
}
