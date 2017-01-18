package datatype.immutable;

/**
 * Created by alexsch on 12/14/2016.
 */

import java.util.function.Function;
import java.util.function.Predicate;

public final class ImmutableList<E> {

    public final E head;
    public final ImmutableList<E> tail;

    public ImmutableList(E head, ImmutableList<E> tail) {
        this.head = head;
        this.tail = tail;
    }

    public static <T> ImmutableList<T> cons(T elem, ImmutableList<T> list) {
        return new ImmutableList<T>(elem, list);
    }

    public <R> ImmutableList<R> map(Function<E, R> mapper) {
        return map(this, mapper);
    }

    public ImmutableList<E> filter(Predicate<E> predicate) {
        return filter(this, predicate);
    }

    public static <T, R> ImmutableList<R> map(ImmutableList<T> list, Function<T, R> mapper) {
        return list == null ? null : new ImmutableList<R>(mapper.apply(list.head), map(list.tail, mapper));
    }

    public ImmutableList<E> reverse() {
        return reverse(this, null);
    }

    private static <T> ImmutableList<T> reverse(ImmutableList<T> list, ImmutableList<T> acc) {
        return list == null ? acc : reverse(list.tail, cons(list.head, acc));
    }


    public static <T> ImmutableList<T> filter(ImmutableList<T> list, Predicate<T> predicate) {
        if (list == null) {
            return null;
        } else if (!predicate.test(list.head)) {
            return filter(list.tail, predicate);
        } else {
            return new ImmutableList<T>(list.head, filter(list.tail, predicate));
        }
    }

    public static <T> ImmutableList<T> listOf(T... elements) {
        ImmutableList<T> list = null;

        for (int i = elements.length - 1; i >= 0; i--) {
            list = cons(elements[i], list);
        }
        return list;
    }
}