package sample.algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by alexsch on 4/22/2017.
 */
public class Sort {


    public static void mergeSort(int[] array) {

        int n = array.length;
        int[] temp = new int[n];

        mergeSort(array, temp, 0, n);
    }

    private static void mergeSort(int[] arr, int[] temp, int from, int to) {

        if (from == to || from + 1 == to) {
            return;
        }

        int mid = (from + to) / 2;

        mergeSort(arr, temp, from, mid);
        mergeSort(arr, temp, mid, to);

        int i1 = from;
        int i2 = mid;
        int i = from;

        while (true) {

            if (i1 == mid) {
                while (i2 < to) {
                    temp[i++] = arr[i2++];
                }
                break;
            }

            if (i2 == to) {
                while (i1 < mid) {
                    temp[i++] = arr[i1++];
                }
                break;
            }

            if (arr[i1] < arr[i2]) {
                temp[i++] = arr[i1++];
            } else {
                temp[i++] = arr[i2++];
            }
        }

        System.arraycopy(temp, from, arr, from, to - from);
    }

    public static void main(String[] args) {

        for (int[] array : ARRAYS) {
            testSort(array);
        }

        testSort(generateRandomArray(7));
        testSort(generateRandomArray(15));
        testSort(generateRandomArray(35));
    }

    private static void testSort(int[] array) {
        System.out.printf("array : %s\n", Arrays.toString(array));
        mergeSort(array);
        System.out.printf("sorted: %s\n", Arrays.toString(array));
        System.out.printf("%n");

        if (!isSorted(array)) {
            throw new RuntimeException("Array is not sorted!");
        }
    }

    private static boolean isSorted(int[] array) {

        int len = array.length;
        int[] copy = Arrays.copyOf(array, len);
        Arrays.sort(copy);

        for (int i = 0; i < len; i++) {
            if (array[i] != copy[i]) {
                return false;
            }
        }

        return true;
    }

    private static int[] generateRandomArray(int n) {
        int[] array = new int[n];
        Random random = new Random();

        int max = 3 * n;
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(max);
        }

        return array;
    }

    private static final int[][] ARRAYS = {
            {7},
            {3, 5},
            {5, 3},
            {1, 3, 5},
            {1, 5, 3},
            {3, 5, 1},
            {3, 1, 5},
            {5, 1, 3},
            {5, 3, 1},
            {5, 1, 3, 4},
            {5, 1, 3, 4, 9, 12, 11, 6},
    };
}