package primewriter;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Buffer buffer = new Buffer(8);
        long max = 10000;
        PrimeProducer primeProducer = new PrimeProducer(buffer, max);
        PrimeConsumer primeConsumer = new PrimeConsumer(buffer);
        primeProducer.start();
        primeConsumer.start();
    }
}
