package primewriter;

import java.io.FileWriter;
import java.io.IOException;

public class AfterConsumption implements RunnableAction {
    private FileWriter fileWriter;

    public AfterConsumption(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public void run(int number) {
        try {
            fileWriter.write(number + " ");
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(number);
    }
}
