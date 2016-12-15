package immutable.datatype;

import datatype.immutable.ImmutableList;

import static datatype.immutable.ImmutableList.*;

/**
 * Created by alexsch on 12/15/2016.
 */
public class ImmutableListTest {
    public static void main(String[] args) {
        testfilter();
    }

    static void testfilter() {

        String first = "first";
        String second = "second";
        String third = "third";

        ImmutableList<String> animals = listOf(first, second, third);

        ImmutableList<String> exceptAll = animals.filter(elem -> false);
        assertTrue(exceptAll == null, "Wrong filter all test!");

        ImmutableList<String> exceptFirst = animals.filter(elem -> !first.equals(elem));
        assertTrue(second.equals(exceptFirst.head), "Wrong filter first test!");
        assertTrue(third.equals(exceptFirst.tail.head), "Wrong filter first test!");

        ImmutableList<String> exceptSecond = animals.filter(elem -> !second.equals(elem));
        assertTrue(first.equals(exceptSecond.head), "Wrong filter second test!");
        assertTrue(third.equals(exceptSecond.tail.head), "Wrong filter second test!");


        ImmutableList<String> exceptThird = animals.filter(elem -> !third.equals(elem));
        assertTrue(first.equals(exceptThird.head), "Wrong filter third test!");
        assertTrue(second.equals(exceptThird.tail.head), "Wrong filter third test!");

    }

    private static void assertTrue(boolean value, String msg) {
        if (!value) {
            throw new RuntimeException(msg);
        }
    }
}
