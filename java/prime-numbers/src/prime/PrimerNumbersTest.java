package prime;

/**
 * Created by alexsch on 11/21/2016.
 */
public class PrimerNumbersTest {

    public static void main(String[] args) {
        calculatePrimeNumbersTime();
    }

    private static void calculatePrimeNumbersTime() {
        int primeNumbersCount = 25000;
        int threadsNumber = 8;

        System.out.printf("Find first %d prime numbers%n", primeNumbersCount);
        testPrimes("sequential", new SequentialPrimeNumbers(), primeNumbersCount);
        testPrimes("sequential", new SynchronizedPrimeNumbers(threadsNumber), primeNumbersCount);
        testPrimes("predefined", new PredefinedSynchronizedPrimeNumbers(threadsNumber), primeNumbersCount);
    }

    private static void testPrimes(String msg, PrimeNumbers primeNumbers, int count) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        primeNumbers.findPrimeNumbers(count);
        stopWatch.stop();
        System.out.printf("[%s] elapsed time: %f seconds%n", msg, stopWatch.getElapsedTimeSeconds());
    }

    private static class StopWatch {
        long lastTime;
        long elapsedTime;

        void start() {
            lastTime = System.currentTimeMillis();
        }

        void stop() {
            elapsedTime = System.currentTimeMillis() - lastTime;
        }

        double getElapsedTimeSeconds() {
            return elapsedTime / 1000.0;
        }
    }
}
