package primewriter;

public class App {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(8);
        long max = 100;
        AfterConsumption action = new AfterConsumption();
        PrimeProducer primeProducer = new PrimeProducer(buffer, max);
        PrimeConsumer primeConsumer = new PrimeConsumer(buffer, action);
        new Thread(primeProducer).start();
        new Thread(primeConsumer).start();
    }
}
