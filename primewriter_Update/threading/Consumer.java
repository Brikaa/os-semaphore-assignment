package primewriter.threading;

import primewriter.jobs.ConsumptionJob;
import primewriter.jobs.JobException;
import primewriter.gui.Notification;

public class Consumer implements Runnable {
    private Buffer buffer;
    private Notification notification;
    // assuming 2 jobs only so far
    ConsumptionJob[] job = new ConsumptionJob [2];

    public Consumer(Buffer buffer, ConsumptionJob job[], Notification notification) {
        this.buffer = buffer;
        this.job = job;
        this.notification = notification;
    }

    public void run() {
        try {
            notification.notifiy("Start");
            job[0].initiate();
            while (true) {
                Object nextConsumed = buffer.consume();
                if (nextConsumed.equals(Producer.DONE_SIGNAL)) {
                    break;
                }
                job[0].run(nextConsumed);
            }
            job[0].cleanup();
            notification.notifiy("Stop");
        } catch (JobException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
             job[1].initiate();
             // well be improved upon later
             Object message = notification.notifiy("Get Elapsed time");
             job[1].run(message);
             job[1].cleanup();
        } catch (JobException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
