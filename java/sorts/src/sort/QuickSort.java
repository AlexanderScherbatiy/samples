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

        T pivot = elems[(end + start) / 2];
        int i = start;
        int j = end;

        while (true) {
            while (less(elems[i], pivot, comparator) && i < j) {
                i++;
            }

            while (less(pivot, elems[j], comparator) && i < j) {
                j--;
            }

            if (i >= j) {
                break;
            }

            swap(i, j, elems);

            i++;
            j--;
        }

        if (i == start) {
            i++;
        }

        sort(elems, start, i - 1, comparator);
        sort(elems, i, end, comparator);
    }


    private <T> void trace(int start, int end, int i, int j, T pivot, T[] elems) {
        
        StringBuilder builder = new StringBuilder();
        for (int l = 0; l < elems.length; l++) {
            if (start == l) {
                builder.append(" |");
            }

            if (i == l) {
                builder.append(" .");
            }

            builder.append(" ").append(elems[l]);

            if (j == l) {
                builder.append(" .");
            }

            if (end == l) {
                builder.append(" |");
            }
        }
        builder.append("  pivot: ").append(pivot);

        System.out.println(builder);
    }
}
