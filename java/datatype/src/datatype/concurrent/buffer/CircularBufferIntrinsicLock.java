package datatype.concurrent.buffer;

import java.util.Arrays;

/**
 * Created by alexsch on 3/1/2017.
 */
public class CircularBufferIntrinsicLock<E> implements CircularBuffer<E> {

    private final int size;
    private final BufferPolicy policy;
    private final E[] buff;
    private int head;
    private int tail;
    private final Object lock = new Object();

    public CircularBufferIntrinsicLock(int size) {
        this(size, BufferPolicy.BLOCK);
    }

    public CircularBufferIntrinsicLock(int size, BufferPolicy policy) {
        this.size = size;
        this.policy = policy;
        buff = (E[]) new Object[size];
    }

    @Override
    public boolean isEmpty() {
        synchronized (lock) {
            return head == tail;
        }
    }

    @Override
    public boolean isFull() {
        synchronized (lock) {
            return tail == nextIndex(head);
        }
    }

    @Override
    public void put(E elem) {
        synchronized (lock) {
            switch (policy) {
                case BLOCK:

                    while (isFull()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    buff[head] = elem;
                    head = nextIndex(head);
                    lock.notifyAll();
                    break;
                default:
                    throw new RuntimeException(
                            String.format("Unknown policy: %s", policy));
            }
        }
    }

    @Override
    public E take() {
        synchronized (lock) {
            switch (policy) {
                case BLOCK:

                    while (isEmpty()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    E elem = buff[tail];
                    buff[tail] = null;
                    tail = nextIndex(tail);
                    lock.notifyAll();
                    return elem;
                default:
                    throw new RuntimeException(
                            String.format("Unknown policy: %s", policy));
            }
        }
    }

    private int nextIndex(int i) {
        int index = i + 1;
        return index < size ? index : 0;
    }

    @Override
    public String toString() {
        return String.format("CircularBufferIntrinsicLock size: %d, [%s]",
                size, Arrays.toString(buff));
    }
}
