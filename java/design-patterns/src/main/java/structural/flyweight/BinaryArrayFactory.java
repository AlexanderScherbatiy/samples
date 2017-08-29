package structural.flyweight;

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

        while (s != 0) {
            list.add((s & 0x1) == 1);
            s = s >> 1;
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
