package prime;

/**
 * Created by alexsch on 11/18/2016.
 */
public class PrimeNumbersSample {

    public static void main(String[] args) {

        printPrimeNumbers("sequential", new SequentialPrimeNumbers().findPrimeNumbers(PN));
        printPrimeNumbers("synchronized" , new SynchronizedPrimeNumbers().findPrimeNumbers(PN));
    }

    private static void printPrimeNumbers(String name, Iterable<Long> primeNumbers){
        System.out.printf("%s%n", name);
        int n = 1;
        for (long prime : primeNumbers) {
            System.out.printf("prime number [%d] = %d%n", n++, prime);
        }
        System.out.println();
    }
}