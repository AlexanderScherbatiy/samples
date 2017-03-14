package datatype.concurrent.lock;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;

/**
 * Created by alexsch on 3/14/2017.
 */
public class SimpleSpinLock implements SimpleLock {

    private final AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {

        while (true) {
            if (!lock.get()) {
                // busy wait
            }

            if (lock.compareAndSet(false, true)) {
                return;
            }
        }
    }

    public void unlock() {
        lock.set(false);
    }
}
