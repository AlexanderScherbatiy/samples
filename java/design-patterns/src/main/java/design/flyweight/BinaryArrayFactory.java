package design.flyweight;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class BinaryArrayFactory {


    private static final HashMap<Integer, boolean[]> cache = new HashMap<>();

    public static boolean[] getBinaryArray(int value) {

        boolean[] array = cache.get(value);

        if (array != null) {
            return array;
        }

        List<Boolean> list = new ArrayList<>();

        int s = value;

        if (s == 0) {
            list.add(false);
        } else {
            while (s != 0) {
                list.add(s % 2 != 0);
                s = s / 2;
            }
        }

        int size = list.size();
        array = new boolean[list.size()];

        for (int i = 0; i < size; i++) {
            array[i] = list.get(size - i - 1);
        }

        cache.put(value, array);
        return array;
    }
}
