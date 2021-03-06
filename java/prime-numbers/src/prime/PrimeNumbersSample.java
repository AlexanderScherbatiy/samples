package prime;

/**
 * Created by alexsch on 11/18/2016.
 */
public class PrimeNumbersSample {

    public static void main(String[] args) {
        int primeNumbersCount = 150;
        int threadsNumber = 8;

        printPrimeNumbers("sequential", new SequentialPrimeNumbers().findPrimeNumbers(primeNumbersCount));
        printPrimeNumbers("synchronized", new SynchronizedPrimeNumbers(threadsNumber).findPrimeNumbers(primeNumbersCount));
        printPrimeNumbers("predefined", new PredefinedSynchronizedPrimeNumbers(threadsNumber).findPrimeNumbers(primeNumbersCount));
    }

    private static void printPrimeNumbers(String name, Iterable<Long> primeNumbers) {
        System.out.printf("%s%n", name);
        int n = 1;
        for (long prime : primeNumbers) {
            System.out.printf("prime number [%d] = %d%n", n++, prime);
        }
        System.out.println();
    }
}