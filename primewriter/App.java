package primewriter;

import primewriter.threading.*;
import primewriter.jobs.*;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class App {
    public static void main(String[] args) throws IOException {
        Buffer buffer = new Buffer(8);
        int max = 50;

        JFrame mainFrame = new JFrame();
        mainFrame.setTitle("Prime Writer");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = mainFrame.getContentPane();
        contentPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
        JLabel lastPrimeLabel = new JLabel();
        JLabel stopWatchLabel = new JLabel();
		c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
		c.gridy = 0;
        c.ipadx = 10;
        contentPane.add(lastPrimeLabel, c);
        c.gridx = 1;
        contentPane.add(stopWatchLabel, c);

        ProductionJob generatePrimeJob = new GeneratePrimeJob(max);
        Producer Producer = new Producer(buffer, generatePrimeJob);

        ConsumptionJobList cJobList = new ConsumptionJobList();
        cJobList
                .addJob(new WriteFileJob("test.txt"))
                .addJob(new PeriodicJob(new WriteLabelJob(lastPrimeLabel), 500))
                .addJob(new PeriodicJob(new StopWatchJob(stopWatchLabel), 500));
        Consumer Consumer = new Consumer(buffer, cJobList);

        mainFrame.setVisible(true);

        new Thread(Producer).start();
        new Thread(Consumer).start();
    }
}
