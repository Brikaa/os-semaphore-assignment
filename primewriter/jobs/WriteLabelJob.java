package primewriter.jobs;

import javax.swing.JLabel;

public class WriteLabelJob implements ConsumptionJob {
    JLabel label;

    public WriteLabelJob(JLabel label) {
        this.label = label;
    }

    public void initiate() {
        label.setText("");
    }

    public void run(Object message) {
        label.setText(message.toString());
    }

    public void cleanup() {
    }
}
