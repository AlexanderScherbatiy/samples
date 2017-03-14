package datatype.concurrent.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Created by alexsch on 3/12/2017.
 */
public class SimpleMutexTest {

    private static volatile boolean interrupted;

    public static void main(String[] args) throws Exception {

        test(new MutexCounter());
        test(new SpinCounter());
    }

    private static void test(final Counter counter) throws Exception {
        final int N = 1000;
        final int steps = 20000;
        final int[] counts = new int[N];
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch finish = new CountDownLatch(N);

        for (int i = 0; i < N; i++) {

            final int index = i;
            new Thread(() -> {
                try {
                    start.await();

                    int count = 0;
                    for (int j = 0; j < steps; j++) {
                        count = counter.getNextCount();
                    }
                    counts[index] = count;

                } catch (InterruptedException e) {
                    interrupted = true;
                    return;
                } finally {
                    finish.countDown();
                }
            }).start();
        }

        start.countDown();
        finish.await(5, TimeUnit.SECONDS);

        if (interrupted) {
            throw new RuntimeException("Thread is interrupted!");
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            if (max < counts[i]) {
                max = counts[i];
            }
        }

        int MAX = N * steps - 1;

        if (max != MAX) {
            throw new RuntimeException(String.format(
                    "Wrong max value %d instead of %d (delta %d)", max, MAX, MAX - max));
        }
    }

    private interface Counter {
        int getNextCount();
    }

    private static abstract class AbstractCounter implements Counter {

        final SimpleLock lock;
        private int count = 0;

        public AbstractCounter(SimpleLock lock) {
            this.lock = lock;
        }

        @Override
        public int getNextCount() {
            lock.lock();
            try {
                return count++;
            } finally {
                lock.unlock();
            }
        }
    }

    private static class MutexCounter extends AbstractCounter {
        public MutexCounter() {
            super(new SimpleMutex());
        }
    }

    private static class SpinCounter extends AbstractCounter {
        public SpinCounter() {
            super(new SimpleSpinLock());
        }
    }
}
