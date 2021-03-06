package samples.immutable;


/**
 * Created by alexsch on 12/14/2016.
 */

import datatype.immutable.ImmutableList;

import static datatype.immutable.ImmutableList.*;

public class ImmutableListSample {

    public static void main(String[] args) {
        printSample();
        mapSample();
        filterSample();
    }

    private static void printSample() {
        System.out.printf("empty   list: %s\n", toString(listOf()));
        System.out.printf("animals list: %s\n", toString(listOf("cat", "dog", "mouse")));
    }

    private static void mapSample() {
        ImmutableList<Integer> numbers = listOf(1, 2, 3, 4, 5);
        ImmutableList<Integer> squares = numbers.map((x) -> x * x);
        System.out.printf("map %s to squares %s\n", toString(numbers), toString(squares));
    }

    private static void filterSample() {
        ImmutableList<String> animals = listOf("cat", "dog", "mouse");
        ImmutableList<String> animalsExceptDog = animals.filter(elem -> !"dog".equals(elem));
        System.out.printf("animals: %s except dog: %s\n", toString(animals), toString(animalsExceptDog));
    }

    private static <T> String toString(ImmutableList<T> list) {

        StringBuilder builder = new StringBuilder();
        builder.append('[');
        while (list != null) {
            builder.append(list.head).append(", ");
            list = list.tail;
        }

        int len = builder.length();
        if (len > 2) {
            builder.setLength(builder.length() - 2);
        }

        builder.append(']');
        return builder.toString();
    }
}
