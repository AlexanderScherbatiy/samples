package sort;

import java.util.Comparator;

/**
 * Created by alexsch.
 */
public interface Sort {


    <T> void sort(T[] elems, Comparator<T> comparable);

    default <T extends Comparable<T>> void sort(T[] elems) {
        sort(elems, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        });
    }
}
