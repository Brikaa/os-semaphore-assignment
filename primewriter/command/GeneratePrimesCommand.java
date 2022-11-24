package primewriter.command;

import javax.swing.JButton;
import javax.swing.JLabel;

import primewriter.jobs.ConsumptionJobList;
import primewriter.jobs.CounterJob;
import primewriter.jobs.CounterWriterJob;
import primewriter.jobs.DisableButtonJob;
import primewriter.jobs.GeneratePrimeJob;
import primewriter.jobs.PeriodicJob;
import primewriter.jobs.StopWatchJob;
import primewriter.jobs.WriteFileJob;
import primewriter.jobs.WriteLabelJob;
import threading.jobs.ProductionJob;
import threading.synchronization.Buffer;
import threading.synchronization.Consumer;
import threading.synchronization.Producer;

public class GeneratePrimesCommand {
    public void run(String outputFileName, int maximumNumber, int bufferSize, JLabel lastPrimeLabel,
            JLabel stopWatchLabel, JLabel counterLabel, JButton button) {
        ConsumptionJobList cJobList = new ConsumptionJobList();
        CounterJob counterJob = new CounterJob();
        cJobList
                .addJob(new WriteFileJob(outputFileName))
                .addJob(counterJob)
                .addJob(new PeriodicJob(new WriteLabelJob(lastPrimeLabel), 500))
                .addJob(new PeriodicJob(new StopWatchJob(stopWatchLabel), 500))
                .addJob(new PeriodicJob(new CounterWriterJob(counterLabel, counterJob), 500))
                .addJob(new DisableButtonJob(button));

        Buffer buffer = new Buffer(bufferSize);
        ProductionJob generatePrime = new GeneratePrimeJob(maximumNumber);
        Producer producer = new Producer(buffer, generatePrime);
        Consumer consumer = new Consumer(buffer, cJobList);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        producerThread.start();
        consumerThread.start();
    }
}
