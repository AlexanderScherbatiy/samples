package datatype.concurrent.buffer;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by alexsch on 3/7/2017.
 */
public class CircularBufferAtomicArray<E> implements CircularBuffer<E> {

    private final int UNLOCK = 0;
    private final int LOCK = 1;
    private final AtomicIntegerArray available;
    private final E[] buff;
    private final int size;
    private AtomicInteger putIndex;
    private AtomicInteger takeIndex;


    public CircularBufferAtomicArray(int size) {
        this.size = size;
        this.available = new AtomicIntegerArray(size);
        this.buff = (E[]) new Object[size];
        putIndex = new AtomicInteger(0);
        takeIndex = new AtomicInteger(0);
    }

    @Override
    public boolean isEmpty() {
        return takeIndex.get() == putIndex.get();
    }

    @Override
    public boolean isFull() {
        return nextIndex(putIndex.get() + 1) == takeIndex.get();
    }

    @Override
    public void put(E elem) {
        while (true) {

            int index = putIndex.get();
            int nextIndex = nextIndex(index);
            if (nextIndex == takeIndex.get()) {
                // buffer is full
                continue;
            }

            if (!available.compareAndSet(index, UNLOCK, LOCK)) {
                // busy waiting
                continue;
            }

            // critical section

            if (index != putIndex.get()) {
                // index has been changed
                continue;
            }

            buff[index] = elem;
            // exclusive access to the put index
            putIndex.set(nextIndex);
            available.set(index, UNLOCK);
            break;
        }
    }

    @Override
    public E take() {

        while (true) {
            int index = takeIndex.get();

            if (index == putIndex.get()) {
                // buffer is empty
                continue;
            }

            if (!available.compareAndSet(index, UNLOCK, LOCK)) {
                // busy waiting
                continue;
            }

            // critical section

            if (index != takeIndex.get()) {
                // index has been changed
                continue;
            }

            E elem = buff[index];
            int nextIndex = nextIndex(index);

            // exclusive access to the take index
            takeIndex.set(nextIndex);
            available.set(index, UNLOCK);
            return elem;
        }
    }

    private int nextIndex(int i) {
        int index = i + 1;
        return index < size ? index : 0;
    }
}
