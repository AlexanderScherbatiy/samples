package sort;

import java.util.Comparator;

/**
 * Created by alexsch.
 */
public abstract class AbstractSort implements Sort {

    protected <T> boolean less(T a, T b, Comparator<T> comparator) {
        return comparator.compare(a, b) < 0;
    }

    protected <T> void swap(int i, int j, T[] elems) {
        T elem = elems[i];
        elems[i] = elems[j];
        elems[j] = elem;
    }

    public static <T> boolean isSorted(T[] elems, Comparator<T> comparator) {

        for (int i = 0; i < elems.length - 1; i++) {
            if (comparator.compare(elems[i], elems[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    public static <T extends Comparable<T>> boolean isSorted(T[] elems) {
        return isSorted(elems, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        });
    }
}
