package datatype.tree;

import java.util.stream.Stream;

/**
 * Created by alexsch on 3/2/2017.
 */
public interface BinarySearchTree<E extends Comparable<E>> extends Iterable<E> {

    boolean contains(E elem);

    void insert(E elem);

    Stream<E> stream();

    Stream<E> parallelStream();
}
