package prime;

/**
 * Created by alexsch on 11/21/2016.
 */
public class PrimerNumbersTest {

    public static void main(String[] args) {
        calculatePrimeNumbersTime();
    }

    private static void calculatePrimeNumbersTime() {
        int primeNumbersCount = 20000;

        System.out.printf("Find first %d prime numbers%n", primeNumbersCount);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        new SequentialPrimeNumbers().findPrimeNumbers(primeNumbersCount);
        stopWatch.stop();
        System.out.printf("[sequential] elapsed time: %f seconds%n", stopWatch.getElapsedTimeSeconds());

        stopWatch.start();
        new SynchronizedPrimeNumbers(11).findPrimeNumbers(primeNumbersCount);
        stopWatch.stop();
        System.out.printf("[sequential] elapsed time: %f seconds%n", stopWatch.getElapsedTimeSeconds());
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
