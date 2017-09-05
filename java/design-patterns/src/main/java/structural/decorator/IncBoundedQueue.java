package structural.decorator;

/**
 * Created by Aleksandr_Shcherbaty on 9/5/2017.
 */
public class IncBoundedQueue implements BoundedQueue {

    private final BoundedQueue queue;

    public IncBoundedQueue(BoundedQueue queue) {
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
        queue.enque(x + 1);
    }

    @Override
    public int deque() {
        return queue.deque();
    }
}
