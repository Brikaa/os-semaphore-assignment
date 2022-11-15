package primewriter.gui;

import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class MainFrame extends JFrame {
    private JLabel lastPrimeLabel;
    private JLabel counterLabel;
    private JLabel stopWatchLabel;
    private JSpinner maximumNumberSpinner;
    private JSpinner bufferSizeSpinner;
    private static final String APP_NAME = "Prime writer";

    public MainFrame(JLabel lastPrimeLabel, JLabel counterLabel, JLabel stopWatchLabel) {
        super();
        this.lastPrimeLabel = lastPrimeLabel;
        this.counterLabel = counterLabel;
        this.stopWatchLabel = stopWatchLabel;
        this.maximumNumberSpinner = new JSpinner(new SpinnerNumberModel(10000000, 2, 1000000000, 1));
        this.maximumNumberSpinner = new JSpinner(new SpinnerNumberModel(8, 2, 1000000000, 1));
    }

    public void setVisible() {
        this.setTitle(APP_NAME);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 10;
        contentPane.add(new JLabel("Maximum number"), c);
        c.gridx = 1;
        contentPane.add(maximumNumberSpinner, c);
        c.gridx = 0;
        c.gridy = 1;
        contentPane.add(new JLabel("Buffer size"), c);
        c.gridx = 1;
        contentPane.add(counterLabel, c);
        this.setVisible(true);
    }
}
