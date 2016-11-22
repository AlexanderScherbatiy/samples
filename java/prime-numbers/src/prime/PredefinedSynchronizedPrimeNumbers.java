package prime;

import java.util.Iterator;

import static java.lang.Long.MAX_VALUE;

/**
 * Created by alexsch on 11/18/2016.
 */
public class PredefinedSynchronizedPrimeNumbers implements PrimeNumbers {

    private final Object lock = new Object();
    private final int threadsNumber;
    private final long[] currentNumbers;
    private final long[] waitNumbers;
    private final PrimeNumberItem head = new PrimeNumberItem(2);
    private PrimeNumberItem tail = head;
    private long currentPrimeNumbersCount = 1;

    public PredefinedSynchronizedPrimeNumbers() {
        this(Runtime.getRuntime().availableProcessors());
    }

    public PredefinedSynchronizedPrimeNumbers(int threadsNumber) {
        synchronized (lock) {
            this.threadsNumber = threadsNumber;
            this.currentNumbers = new long[threadsNumber];
            this.waitNumbers = new long[threadsNumber];
            for (int i = 0; i < threadsNumber; i++) {
                waitNumbers[i] = MAX_VALUE;
            }
        }
    }

    @Override
    public Iterable<Long> findPrimeNumbers(long primeNumbersCount) {

        findPredefinedPrimeNumbers(primeNumbersCount);
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

    public void findPredefinedPrimeNumbers(long primeNumbersCount) {

        long n = 2;
        long predefinedNumbersCount = (threadsNumber == 1)
                ? primeNumbersCount
                : Math.min(primeNumbersCount, threadsNumber);

        boolean sequential = (predefinedNumbersCount == primeNumbersCount);

        boolean[] skipThread = new boolean[threadsNumber];

        mainLoop:
        while (currentPrimeNumbersCount < predefinedNumbersCount) {
            n++;
            PrimeNumberItem current = head;
            while (current != null) {
                if (n % current.prime == 0) {
                    continue mainLoop;
                }
                current = current.next;
            }

            tail.next = new PrimeNumberItem(n);
            tail = tail.next;
            currentPrimeNumbersCount++;

            if (!sequential && threadsNumber % n == 0) {
                for (int i = 0; i < threadsNumber; i++) {
                    if (!skipThread[i] && ((3 + i) % n == 0)) {
                        skipThread[i] = true;
                        synchronized (lock) {
                            currentNumbers[i] = MAX_VALUE;
                        }
                    }
                }
            }
        }

        if (sequential) {
            return;
        }

        final Thread[] threads = new Thread[threadsNumber];

        for (int i = 0; i < threadsNumber; i++) {

            if (skipThread[i]) {
                continue;
            }

            final int threadIndex = i;

            Thread thread = new Thread(() -> {

                long k = 3 + threadIndex;

                mainLoop:
                while (true) {
                    k += threadsNumber;
                    synchronized (lock) {

                        if (primeNumbersCount <= currentPrimeNumbersCount) {
                            currentNumbers[threadIndex] = MAX_VALUE;
                            if (waitNumbers[threadIndex] != MAX_VALUE) {
                                lock.notifyAll();
                            }
                            return;
                        }

                        currentNumbers[threadIndex] = k;

                        if (waitNumbers[threadIndex] < k) {
                            waitNumbers[threadIndex] = MAX_VALUE;
                            lock.notifyAll();
                        }
                    }

                    PrimeNumberItem current = head;
                    PrimeNumberItem last = null;

                    while (current != null) {

                        final long prime = current.prime;

                        if (k % prime == 0) {
                            continue mainLoop;
                        }

                        last = current;
                        current = current.next;
                    }

                    int j = 0;
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
                                currentNumbers[threadIndex] = MAX_VALUE;
                                if (waitNumbers[threadIndex] != MAX_VALUE) {
                                    lock.notifyAll();
                                }
                                return;
                            }

                            for (; j < threadsNumber; j++) {
                                if (currentNumbers[j] < k) {
                                    waitNumbers[j] = Math.min(k, waitNumbers[j]);
                                    try {
                                        lock.wait();
                                    } catch (Exception e) {
                                        currentNumbers[threadIndex] = Long.MAX_VALUE;
                                        throw new RuntimeException(e);
                                    }
                                    continue tailLoop;
                                }
                            }

                            tail.next = new PrimeNumberItem(k);
                            tail = tail.next;
                            currentPrimeNumbersCount++;
                            continue mainLoop;
                        }
                    }
                }
            });

            thread.start();
            threads[i] = thread;
        }

        try {
            for (int i = 0; i < threadsNumber; i++) {
                if (threads[i] != null) {
                    threads[i].join();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static class PrimeNumberItem {
        private final long prime;
        private volatile PrimeNumberItem next;

        private PrimeNumberItem(long n) {
            this.prime = n;
        }
    }
}