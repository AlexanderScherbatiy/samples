package datatype.immutable;

/**
 * Created by alexsch.
 */
public class ImmutableQueue<E> {

    private final ImmutableList<E> leading;
    private final ImmutableList<E> trailing;

    public ImmutableQueue() {
        this(null, null);
    }

    private ImmutableQueue(ImmutableList<E> leading, ImmutableList<E> trailing) {
        this.leading = leading;
        this.trailing = trailing;
    }

    public ImmutableQueue<E> enqueue(E elem) {
        return new ImmutableQueue<E>(leading, ImmutableList.cons(elem, trailing));
    }

    public E head() {
        return mirror().leading.head;
    }

    public ImmutableQueue<E> tail() {
        final ImmutableQueue<E> queue = mirror();
        return new ImmutableQueue<E>(queue.leading.tail, queue.trailing);
    }

    public static <T> ImmutableQueue<T> queueOf(T... elements) {
        return new ImmutableQueue<T>(ImmutableList.listOf(elements), null);
    }

    private ImmutableQueue<E> mirror() {
        return leading == null ? new ImmutableQueue(trailing == null ? null : trailing.reverse(), null) : this;
    }
}
