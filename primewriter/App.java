package primewriter;

import primewriter.threading.*;
import primewriter.jobs.*;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class App {
    public static void main(String[] args) throws IOException {
        /*
         * Ideas for GUI:
         * - Create a package, primewriter.gui
         *
         * Make a StopWatchJob, initiate() sets the stopwatch to zero, run() increments it to the
         * time delta since it was last run
         *
         * - Make a WriteLabelJob to be able to write to both the file and label
         * at the same time
         *
         * - Make a CounterJob
         *
         * - Create a LazyJob adapter that only runs the job after a certain number of run() calls
         * (to avoid over-updating the GUI), this could be used with the WriteLabelJob and the StopWatchJob
         */
        Buffer buffer = new Buffer(8);
        int max = 50;

        JFrame mainFrame = new JFrame();
        mainFrame.setTitle("Prime Writer");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel lastPrimeLabel = new JLabel();
        mainFrame.add(lastPrimeLabel);

        ProductionJob generatePrimeJob = new GeneratePrimeJob(max);
        Producer Producer = new Producer(buffer, generatePrimeJob);

        ConsumptionJobList cJobList = new ConsumptionJobList();
        cJobList
            .addJob(new WriteFileJob("test.txt"))
            .addJob(new PeriodicJob(new WriteLabelJob(lastPrimeLabel), 500));
        Consumer Consumer = new Consumer(buffer, cJobList);

        mainFrame.setVisible(true);

        new Thread(Producer).start();
        new Thread(Consumer).start();
    }
}
