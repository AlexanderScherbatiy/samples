package datatype.concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by alexsch.
 */
public class BlockingValue<T> {

    private T value;
    private final Sync sync = new Sync();

    public BlockingValue() {
        sync.acquire(1);
    }

    private static final class Sync extends AbstractQueuedSynchronizer {

        boolean isSet() {
            return getState() == 1;
        }

        @Override
        protected boolean isHeldExclusively() {
            return true;
        }

        @Override
        protected boolean tryAcquire(int acquires) {
            assert acquires == 1;
            return compareAndSetState(0, 1);
        }

        @Override
        protected boolean tryRelease(int releases) {
            assert releases == 1;
            return compareAndSetState(1, 0);
        }
    }


    public boolean isSet() {
        return sync.isSet();
    }

    public T getValue() {
        sync.acquire(1);
        T currValue = value;
        value = null;
        return currValue;
    }

    public void setValue(T value) {

        this.value = value;
        boolean release = sync.release(1);
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
