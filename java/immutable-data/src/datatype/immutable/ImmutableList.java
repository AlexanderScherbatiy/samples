package datatype.immutable;

/**
 * Created by alexsch on 12/14/2016.
 */

import java.util.function.Function;

public final class ImmutableList<E> {

    public final E head;
    public final ImmutableList<E> tail;

    public ImmutableList(E head, ImmutableList<E> tail) {
        this.head = head;
        this.tail = tail;
    }

    public static <T, R> ImmutableList<R> map(ImmutableList<T> list, Function<T, R> mapper) {
        ImmutableList<R> resultList = null;

        while (list != null) {
            resultList = new ImmutableList<R>(mapper.apply(list.head), resultList);
            list = list.tail;
        }

        return resultList;
    }

    public static <T> ImmutableList<T> listOf(T... elements) {
        ImmutableList<T> list = null;

        for (int i = elements.length - 1; i >= 0; i--) {
            list = new ImmutableList<T>(elements[i], list);
        }
        return list;
    }
}