package primewriter.jobs;

import javax.swing.JLabel;

public class CounterWriterJob implements ConsumptionJob {
    private JLabel label;
    private CounterJob counterJob;

    public CounterWriterJob(JLabel label, CounterJob counterJob) {
        this.label = label;
        this.counterJob = counterJob;
    }

    public void initiate() {
        label.setText("");
    }

    public void run(Object message) {
        label.setText(Integer.toString(counterJob.getCount()));
    }

    public void cleanup() {
    }
}
