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
         * Make a StopWatchJob, initiate(), and cleanup() set the stopwatch to zero, run() increments it to the
         * time delta since it was last run
         *
         * - Maybe create a WriteLabelJob to be able to write to both the file and label
         * at the same time
         * - Consumer then receives an array of jobs
         *
         * - Create a LazyJob adapter that only runs the job after a certain number of run() calls
         * (to avoid over-updating the GUI), this could be used with the WriteLabelJob and the StopWatchJob
         */
        Buffer buffer = new Buffer(8);
        int max = 150;

        ConsumptionJob writeFileJob = new WriteFileJob("test.txt");
        ProductionJob generatePrimeJob = new GeneratePrimeJob(max);
        ConsumptionJobList cJobList = new ConsumptionJobList();
        cJobList.addJob(writeFileJob);
        Producer Producer = new Producer(buffer, generatePrimeJob);
        Consumer Consumer = new Consumer(buffer, cJobList);

        new Thread(Producer).start();
        new Thread(Consumer).start();
    }
}
