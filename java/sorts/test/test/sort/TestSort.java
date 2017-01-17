package test.sort;

/**
 * Created by alexsch.
 */

import sort.QuickSort;
import sort.Sort;

import java.util.Arrays;

public class TestSort {


    public static void main(String[] args) {

        for (Sort sort : SORTS) {

            for (Integer[][] tests : TESTS_INT) {
                Integer[] test = Arrays.copyOf(tests[0], tests[0].length);
                Integer[] golden = tests[1];
                sort.sort(test);
                System.out.printf("sort %s -> %s\n", Arrays.toString(tests[0]), Arrays.toString(test));
                compare(test, golden);
            }
        }
    }

    private static void compare(Integer[] test, Integer[] golden) {
        if (!Arrays.equals(test, golden)) {
            throw new RuntimeException("");
        }
    }

    private static final Sort[] SORTS = {

            new QuickSort(),
    };
    private static final Integer[][][] TESTS_INT = {
            {{1}, {1}},
            {{1, 1}, {1, 1}},
            {{1, 2}, {1, 2}},
            {{2, 1}, {1, 2}},
            {{1, 1, 1}, {1, 1, 1}},
            {{1, 2, 1}, {1, 1, 2}},
            {{1, 1, 2}, {1, 1, 2}},
            {{2, 1, 1}, {1, 1, 2}},
            {{1, 2, 3}, {1, 2, 3}},
    };
}
