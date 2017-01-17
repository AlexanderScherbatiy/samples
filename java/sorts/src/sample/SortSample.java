package sample;

import sort.AbstractSort;
import sort.QuickSort;
import sort.Sort;
import sort.BubbleSort;

import java.util.Arrays;

/**
 * Created by alexsch.
 */
public class SortSample {

    public static void main(String[] args) {

        Sort sort = new QuickSort();
        Integer[] array = {3, 5, 1, 2, 6, 7, 4};

        System.out.printf("initial array: %s\n", Arrays.toString(array));
        sort.sort(array);
        System.out.printf("sorted  array: %s\n", Arrays.toString(array));
        System.out.printf("is sorted: %b\n", AbstractSort.isSorted(array));
    }
}
