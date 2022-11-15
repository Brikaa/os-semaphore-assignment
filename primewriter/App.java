package primewriter;

import primewriter.threading.*;
import primewriter.gui.MainFrame;
import primewriter.jobs.*;
import java.io.IOException;

import javax.swing.JLabel;

public class App {
    public static void main(String[] args) throws IOException {
        // Buffer buffer = new Buffer(8);
        // int max = 7;

        JLabel lastPrimeLabel = new JLabel();
        JLabel stopWatchLabel = new JLabel();
        JLabel counterLabel = new JLabel();

        // ProductionJob generatePrimeJob = new GeneratePrimeJob(max);
        // Producer Producer = new Producer(buffer, generatePrimeJob);

        // ConsumptionJobList cJobList = new ConsumptionJobList();
        // CounterJob counterJob = new CounterJob();
        // cJobList
        // .addJob(new WriteFileJob("test.txt"))
        // .addJob(counterJob)
        // .addJob(new PeriodicJob(new WriteLabelJob(lastPrimeLabel), 500))
        // .addJob(new PeriodicJob(new StopWatchJob(stopWatchLabel), 500))
        // .addJob(new PeriodicJob(new CounterWriterJob(counterLabel, counterJob),
        // 500));
        // Consumer Consumer = new Consumer(buffer, cJobList);

        // major issue is how to pass the parameters to the command class directly after
        // the button has been clicked
        // also how to re enable button clicks after disabling it

        MainFrame mainFrame = new MainFrame(lastPrimeLabel, counterLabel, stopWatchLabel);
        mainFrame.setVisible();
        String[] array = mainFrame.GetValues();
        ConsumptionJobList cJobList = new ConsumptionJobList();
        CounterJob counterJob = new CounterJob();
        cJobList
                .addJob(new WriteFileJob(array[0]))
                .addJob(counterJob)
                .addJob(new PeriodicJob(new WriteLabelJob(lastPrimeLabel), 500))
                .addJob(new PeriodicJob(new StopWatchJob(stopWatchLabel), 500))
                .addJob(new PeriodicJob(new CounterWriterJob(counterLabel, counterJob), 500));
<<<<<<< Updated upstream
        Consumer Consumer = new Consumer(buffer, cJobList);

        MainFrame mainFrame = new MainFrame(lastPrimeLabel, counterLabel, stopWatchLabel);
        mainFrame.pack();
        mainFrame.setVisible();
=======
        Command C1 = new Intialize(Integer.parseInt(array[1]), Integer.parseInt(array[2]), cJobList);
        mainFrame.setCommand(C1);
>>>>>>> Stashed changes

        // mainFrame.setVisible();
        // new Thread(Producer).start();
        // new Thread(Consumer).start();
    }
}
