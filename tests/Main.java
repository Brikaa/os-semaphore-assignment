package tests;
import primewriter.Util;

public class Main {
    public static void test(int number, boolean expected) {
        System.out.println(
            number + (Util.isPrime(number) == expected ? " succeeds" : " fails") + " (" + expected + ")"
        );
    }

    public static void main(String[] args) {
        System.out.println("IsPrime test");
        test(0, false);
        test(1, false);
        test(2, true);
        test(3, true);
        test(4, false);
        test(7, true);
        test(9, false);
        test(1223, true);
        test(8273, true);
        test(8275, false);
        test(12323, true);
        test(12363, false);
        test(374701, true);
        test(374702, false);
        test(999983, true);
        test(1000000, false);
    }
}
