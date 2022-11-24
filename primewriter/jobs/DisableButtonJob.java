package primewriter.jobs;

import javax.swing.JButton;

import threading.jobs.ConsumptionJob;

public class DisableButtonJob implements ConsumptionJob {
    private JButton button;

    public DisableButtonJob(JButton button) {
        this.button = button;
    }

    public void initiate() {
        button.setEnabled(false);
    }

    public void run(Object message) {
    }

    public void cleanup() {
        button.setEnabled(true);
    }
}
