package structural.decorator;

public interface BoundedQueue {

    boolean isEmpty();

    boolean isFull();

    void enque(int x);

    int deque();
}
