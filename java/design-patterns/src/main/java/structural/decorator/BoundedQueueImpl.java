package structural.decorator;

public class BoundedQueueImpl implements BoundedQueue {

    private int first;
    private int last;
    private final int[] buff;

    public BoundedQueueImpl(int capacity) {
        this.buff = new int[capacity + 1];
    }

    @Override
    public boolean isEmpty() {
        return first == last;
    }

    @Override
    public boolean isFull() {
        return next(last) == first;
    }

    @Override
    public void enque(int x) {
        buff[last] = x;
        last = next(last);
    }

    @Override
    public int deque() {
        int value = buff[first];
        first = next(first);
        return value;
    }

    private int next(int i) {
        int n = i + 1;
        return (n == buff.length) ? 0 : n;
    }
}
