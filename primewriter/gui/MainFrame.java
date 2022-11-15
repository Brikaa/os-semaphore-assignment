package primewriter.gui;

import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class MainFrame extends JFrame {
    private JLabel lastPrimeLabel;
    private JLabel counterLabel;
    private JLabel stopWatchLabel;
    private JSpinner maximumNumberSpinner;
    private JSpinner bufferSizeSpinner;
    private JTextField outputFileNameTextField;
    private JButton startButton;
    private static final String APP_NAME = "Prime writer";

    public MainFrame(JLabel lastPrimeLabel, JLabel counterLabel, JLabel stopWatchLabel) {
        super();
        this.lastPrimeLabel = lastPrimeLabel;
        this.counterLabel = counterLabel;
        this.stopWatchLabel = stopWatchLabel;
        this.maximumNumberSpinner = new JSpinner(new SpinnerNumberModel(10000000, 2, 1000000000, 1));
        this.bufferSizeSpinner = new JSpinner(new SpinnerNumberModel(8, 2, 1000000000, 1));
        this.outputFileNameTextField = new JTextField("primes.txt");
        this.startButton = new JButton("Start producer");
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
        c.insets.left = 10;
        c.insets.top = 5;
        contentPane.add(new JLabel("Maximum number"), c);
        c.gridx = 1;
        contentPane.add(maximumNumberSpinner, c);
        c.gridx = 0;
        c.gridy = 1;
        contentPane.add(new JLabel("Buffer size"), c);
        c.gridx = 1;
        contentPane.add(bufferSizeSpinner, c);
        c.gridx = 0;
        c.gridy = 2;
        contentPane.add(new JLabel("Output file"), c);
        c.gridx = 1;
        contentPane.add(outputFileNameTextField, c);
        c.gridx = 0;
        c.gridy = 3;
        contentPane.add(startButton, c);
        this.setVisible(true);
    }
}
