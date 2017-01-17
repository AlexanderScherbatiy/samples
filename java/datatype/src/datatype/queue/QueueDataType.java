package datatype.queue;

/**
 * Created by alexsch.
 */
public interface QueueDataType<T> {

    boolean isEmpty();

    void enqueue(T elem);

    T dequeue();
}
