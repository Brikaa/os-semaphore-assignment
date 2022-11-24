package primewriter.jobs;

import java.io.FileWriter;
import java.io.IOException;

import threading.jobs.ConsumptionJob;
import threading.jobs.exceptions.JobCleanupException;
import threading.jobs.exceptions.JobException;
import threading.jobs.exceptions.JobInitializationException;
import threading.jobs.exceptions.JobRunException;

public class WriteFileJob implements ConsumptionJob {
    private String outputFileName;
    private FileWriter fileWriter;
    private String firstWrite;

    public WriteFileJob(String outputFileName) {
        this.outputFileName = outputFileName;
        firstWrite = "";
    }

    public void initiate() throws JobException {
        try {
            fileWriter = new FileWriter(outputFileName);
        } catch (IOException e) {
            throw new JobInitializationException("Can't open file: " + outputFileName);
        }
    }

    public void run(Object obj) throws JobException {
        try {
            fileWriter.write(firstWrite + "\"" + obj.toString() + "\"");
            firstWrite = ", ";
        } catch (IOException e) {
            throw new JobRunException("Can't write to file: " + outputFileName);
        }
    }

    public void cleanup() throws JobException {
        firstWrite = "";
        try {
            fileWriter.write("\n");
        } catch (IOException e) {
            throw new JobCleanupException("Can't write final newline to file: " + outputFileName);
        }
        try {
            fileWriter.close();
        } catch (IOException e) {
            throw new JobCleanupException("Can't close file: " + outputFileName);
        }
    }
}
