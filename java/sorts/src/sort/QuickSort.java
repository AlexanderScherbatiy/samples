package sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by alexsch.
 */
public class QuickSort extends AbstractSort {


    @Override
    public <T> void sort(T[] elems, Comparator<T> comparator) {
        sort(elems, 0, elems.length - 1, comparator);

    }

    private <T> void sort(T[] elems, int start, int end, Comparator<T> comparator) {

        if (end <= start) {
            return;
        }

        T pivot = elems[(end - start) / 2];
        int i = start;
        int j = end;

        while (true) {
            while (less(elems[i], pivot, comparator)) {
                i++;
                if (i >= j) {
                    break;
                }
            }
            while (greater(elems[j], pivot, comparator)) {
                j--;
                if (i >= j) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }

            swap(i, j, elems);

            i++;
            j--;

            if (i >= j) {
                break;
            }
        }

        int k = i == j ? i : j;

        sort(elems, start, k, comparator);
        sort(elems, k + 1, end, comparator);
    }

    private <T> void print(T[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}
