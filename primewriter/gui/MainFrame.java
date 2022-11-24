package primewriter.gui;

import java.util.Arrays;
import java.util.LinkedList;

import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

    private void doubleGrid(JPanel parent, LinkedList<JComponent> components) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets.left = 10;
        c.insets.top = 5;
        int size = components.size();
        for (int i = 0; i < size; ++i) {
            c.gridy = i;
            for (int j = 0; j < 2; ++j) {
                if (components.size() == 0) {
                    break;
                }
                c.gridx = j;
                JComponent component = components.remove();
                parent.add(component, c);
            }
        }
    }

    public void setVisible() {
        this.setTitle(APP_NAME);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = this.getContentPane();

        JPanel parentPanel = new JPanel();
        contentPane.add(parentPanel);
        parentPanel.setLayout(new GridBagLayout());
        GridBagConstraints parentConstraints = new GridBagConstraints();
        parentConstraints.fill = GridBagConstraints.HORIZONTAL;
        parentConstraints.insets.left = 10;
        parentConstraints.insets.top = 25;
        parentConstraints.gridy = 0;
        parentConstraints.gridx = 0;

        JPanel inputPanel = new JPanel();
        parentPanel.add(inputPanel, parentConstraints);
        inputPanel.setLayout(new GridBagLayout());

        doubleGrid(
                inputPanel, new LinkedList<JComponent>(Arrays.asList(
                        new JLabel("Maximum number"),
                        maximumNumberSpinner,
                        new JLabel("Buffer size"),
                        bufferSizeSpinner,
                        new JLabel("Output file"),
                        outputFileNameTextField,
                        startButton)));

        parentConstraints.gridy = 1;
        JPanel outputPanel = new JPanel();
        parentPanel.add(outputPanel, parentConstraints);
        outputPanel.setLayout(new GridBagLayout());
        doubleGrid(outputPanel, new LinkedList<JComponent>(Arrays.asList(
                new JLabel("Largest prime number: "),
                lastPrimeLabel,
                new JLabel("Number of prime numbers generated: "),
                counterLabel,
                new JLabel("Time elapsed: "),
                stopWatchLabel)));

        this.setVisible(true);
    }
}
