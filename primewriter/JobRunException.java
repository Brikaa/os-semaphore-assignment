package primewriter;

public class JobRunException extends JobException {
    public JobRunException(String message) {
        super("Failed to run job: " + message);
    }
}
