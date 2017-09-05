package structural.decorator;

/**
 * Created by Aleksandr_Shcherbaty on 9/5/2017.
 */
public class DoubleBoundedQueue implements BoundedQueue {

    private final BoundedQueue queue;

    public DoubleBoundedQueue(BoundedQueue queue) {
        this.queue = queue;
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public boolean isFull() {
        return queue.isFull();
    }

    @Override
    public void enque(int x) {
        queue.enque(2 * x);
    }

    @Override
    public int deque() {
        return queue.deque();
    }
}
