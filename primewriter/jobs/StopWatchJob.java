package primewriter.jobs;

import javax.swing.JLabel;

import threading.jobs.ConsumptionJob;

public class StopWatchJob implements ConsumptionJob {
    private JLabel label;
    private long startTime;

    public StopWatchJob(JLabel label) {
        this.label = label;
        this.startTime = 0;
    }

    public void initiate() {
        this.label.setText("");
        this.startTime = System.currentTimeMillis();
    }

    public void run(Object string) {
        this.label.setText(Long.toString(System.currentTimeMillis() - startTime) + " ms");
    }

    public void cleanup() {
        startTime = 0;
    }
}
