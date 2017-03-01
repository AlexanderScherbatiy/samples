package datatype.concurrent.buffer;

/**
 * Created by alexsch on 3/1/2017.
 */
public interface CircularBuffer<E> {

    boolean isEmpty();

    boolean isFull();

    void put(E elem);

    E take();

    enum BufferPolicy {
        BLOCK
    }
}
