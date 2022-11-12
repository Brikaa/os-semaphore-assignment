package primewriter;
import java.lang.Math;

public class Util {
    public static boolean isPrime(int number) {
        if (number == 1 || number == 0) {
            return false;
        }
        double root = Math.floor(Math.sqrt(number));
        for (int i = 2; i <= root; ++i) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
