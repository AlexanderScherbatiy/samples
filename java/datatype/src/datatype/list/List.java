package datatype.list;

/**
 * Created by alexsch on 3/9/2017.
 */
public interface List<E> extends Iterable<E> {

    boolean isEmpty();

    void addHead(E elem);

    void addTail(E elem);
}
