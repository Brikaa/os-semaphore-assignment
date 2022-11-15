package primewriter;

import primewriter.threading.*;
import primewriter.gui.MainFrame;
import primewriter.jobs.*;
import java.io.IOException;

import javax.swing.JLabel;

public class App {
    public static void main(String[] args) throws IOException {
        Buffer buffer = new Buffer(8);
        int max = 7;

        JLabel lastPrimeLabel = new JLabel();
        JLabel stopWatchLabel = new JLabel();
        JLabel counterLabel = new JLabel();

        ProductionJob generatePrimeJob = new GeneratePrimeJob(max);
        Producer Producer = new Producer(buffer, generatePrimeJob);

        ConsumptionJobList cJobList = new ConsumptionJobList();
        CounterJob counterJob = new CounterJob();
        cJobList
                .addJob(new WriteFileJob("test.txt"))
                .addJob(counterJob)
                .addJob(new PeriodicJob(new WriteLabelJob(lastPrimeLabel), 500))
                .addJob(new PeriodicJob(new StopWatchJob(stopWatchLabel), 500))
                .addJob(new PeriodicJob(new CounterWriterJob(counterLabel, counterJob), 500));
        Consumer Consumer = new Consumer(buffer, cJobList);

        MainFrame mainFrame = new MainFrame(lastPrimeLabel, counterLabel, stopWatchLabel);
        mainFrame.pack();
        mainFrame.setVisible();

        // new Thread(Producer).start();
        // new Thread(Consumer).start();
    }
}
