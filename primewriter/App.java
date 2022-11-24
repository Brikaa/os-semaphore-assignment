package primewriter;

import primewriter.gui.MainFrame;
import primewriter.command.*;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        GeneratePrimesCommand generatePrimesCommand = new GeneratePrimesCommand();
        MainFrame mainFrame = new MainFrame(generatePrimesCommand);
        mainFrame.setVisible();
    }
}
