package sort;

import java.util.Comparator;

/**
 * Created by alexsch.
 */
public class BubbleSort extends AbstractSort {

    @Override
    public <T> void sort(T[] elems, Comparator<T> comparator) {

        for (int i = elems.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                int k = j + 1;
                if (!less(j, k, elems, comparator)) {
                    swap(j, k, elems);
                }
            }
        }
    }

    private <T> boolean less(int i, int j, T[] elems, Comparator<T> comparator) {
        return less(elems[i], elems[j], comparator);
    }
}
