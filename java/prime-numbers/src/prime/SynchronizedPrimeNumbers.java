package prime;

import java.util.Iterator;

/**
 * Created by alexsch on 11/18/2016.
 */
public class SynchronizedPrimeNumbers implements PrimeNumbers {

    private final int threadsNumber;
    private final Object lock = new Object();
    private final long[] checkPrimes;
    private final PrimeNumberItem head = new PrimeNumberItem(2);
    private PrimeNumberItem tail = head;
    private long currentPrimeNumbersCount = 1;

    public SynchronizedPrimeNumbers() {
        this(Runtime.getRuntime().availableProcessors());
    }

    public SynchronizedPrimeNumbers(int threadsNumber) {
        this.threadsNumber = threadsNumber;
        this.checkPrimes = new long[threadsNumber];
    }

    @Override
    public Iterable<Long> findPrimeNumbers(final long primeNumbersCount) {

        final Thread[] threads = new Thread[threadsNumber];

        for (int i = 0; i < threadsNumber; i++) {

            final int threadIndex = i;

            Thread thread = new Thread(() -> {

                long k = 3 + threadIndex - threadsNumber;

                mainLoop:
                while (true) {
                    k += threadsNumber;
                    synchronized (lock) {
                        checkPrimes[threadIndex] = k;
                        if (primeNumbersCount <= currentPrimeNumbersCount) {
                            return;
                        }
                    }

                    PrimeNumberItem current = head;
                    PrimeNumberItem last = null;

                    while (current != null) {

                        if (k % current.prime == 0) {
                            continue mainLoop;
                        }

                        last = current;
                        current = current.next;
                    }

                    tailLoop:
                    while (true) {

                        while (last.next != null) {
                            last = last.next;
                            if (k % last.prime == 0) {
                                continue mainLoop;
                            }
                        }

                        synchronized (lock) {
                            if (primeNumbersCount <= currentPrimeNumbersCount) {
                                return;
                            }

                            for (int j = 0; j < threadsNumber; j++) {
                                if (checkPrimes[j] < k) {
                                    continue tailLoop;
                                }
                            }

                            tail.next = new PrimeNumberItem(k);
                            tail = tail.next;
                            currentPrimeNumbersCount++;
                            break;
                        }
                    }
                }
            });

            thread.start();
            threads[i] = thread;
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return () ->
                new Iterator<Long>() {

                    PrimeNumberItem item = head;

                    @Override
                    public boolean hasNext() {
                        return item != null;
                    }

                    @Override
                    public Long next() {
                        long prime = item.prime;
                        item = item.next;
                        return prime;
                    }
                };
    }


    private static class PrimeNumberItem {
        private final long prime;
        private volatile PrimeNumberItem next;

        private PrimeNumberItem(long n) {
            this.prime = n;
        }
    }
}