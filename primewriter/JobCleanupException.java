package primewriter;

public class JobCleanupException extends JobException {
    public JobCleanupException(String message) {
        super("Failed to clean up job: " + message);
    }
}
