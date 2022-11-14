package primewriter.jobs.exceptions;

public class JobInitializationException extends JobException {
    public JobInitializationException(String message) {
        super("Failed to initialize job: " + message);
    }
}
