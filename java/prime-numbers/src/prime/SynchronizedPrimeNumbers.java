package prime;

import java.util.Iterator;
import static java.lang.Long.MAX_VALUE;

/**
 * Created by alexsch on 11/18/2016.
 */
public class SynchronizedPrimeNumbers implements PrimeNumbers {

    private static final int SKIP_STATE_INITIAL = 0;
    private static final int SKIP_STATE_CHECK = 1;
    private static final int SKIP_STATE_COMPLETED = 2;

    private final Object lock = new Object();
    private final int threadsNumber;
    private final long[] currentNumbers;
    private final long[] waitNumbers;
    private final PrimeNumberItem head = new PrimeNumberItem(2);
    private PrimeNumberItem tail = head;
    private long currentPrimeNumbersCount = 1;

    public SynchronizedPrimeNumbers() {
        this(Runtime.getRuntime().availableProcessors());
    }

    public SynchronizedPrimeNumbers(int threadsNumber) {
        this.threadsNumber = threadsNumber;
        this.currentNumbers = new long[threadsNumber];
        this.waitNumbers = new long[threadsNumber];
        for (int i = 0; i < threadsNumber; i++) {
            waitNumbers[i] = MAX_VALUE;
        }
    }

    @Override
    public Iterable<Long> findPrimeNumbers(final long primeNumbersCount) {

        final Thread[] threads = new Thread[threadsNumber];

        for (int i = 0; i < threadsNumber; i++) {

            final int threadIndex = i;

            Thread thread = new Thread(() -> {

                final int initialValue = 3 + threadIndex;
                long k = initialValue - threadsNumber;
                int skipState = SKIP_STATE_INITIAL;

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

                        if (skipState == SKIP_STATE_COMPLETED) {
                            // nop
                        } else if (skipState == SKIP_STATE_CHECK) {
                            skipState = SKIP_STATE_COMPLETED;
                        } else if (skipState == SKIP_STATE_INITIAL && threadsNumber <= tail.prime) {
                            skipState = SKIP_STATE_CHECK;
                        }
                    }

                    PrimeNumberItem current = head;
                    PrimeNumberItem last = null;

                    while (current != null) {

                        final long prime = current.prime;

                        if (skipState == SKIP_STATE_CHECK) {
                            if (threadsNumber % prime == 0 && initialValue % prime == 0) {
                                synchronized (lock) {
                                    currentNumbers[threadIndex] = MAX_VALUE;
                                    if (waitNumbers[threadIndex] != MAX_VALUE) {
                                        lock.notifyAll();
                                    }
                                    return;
                                }
                            }
                        }

                        if (k % prime == 0) {
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
                                continue mainLoop;
                            }

                            for (int j = 0; j < threadsNumber; j++) {
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