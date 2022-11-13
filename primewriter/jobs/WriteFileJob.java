package primewriter.jobs;

import java.io.FileWriter;
import java.io.IOException;

public class WriteFileJob implements Job {
    private String outputFileName;
    private FileWriter fileWriter;

    public WriteFileJob(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    public void initiate() throws JobException {
        try {
            fileWriter = new FileWriter(outputFileName);
        } catch (IOException e) {
            throw new JobInitializationException("Can't open file: " + outputFileName);
        }
    }

    public void run(int number) throws JobException {
        try {
            fileWriter.write(number + " ");
        } catch (IOException e) {
            throw new JobRunException("Can't write to file: " + outputFileName);
        }
        System.out.println(number);
    }

    public void cleanup() throws JobException {
        try {
            fileWriter.close();
        } catch (IOException e) {
            throw new JobCleanupException("Can't close file: " + outputFileName);
        }
    }
}
