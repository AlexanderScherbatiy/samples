package structural.bridge;

import java.util.HashMap;
import java.util.Map;

public class LowLevelStringStorage implements LowLevelStorage {

    private final Map<String, String> map = new HashMap<>();

    @Override
    public void save(String key, int... values) {


        StringBuilder builder = new StringBuilder();

        for (int value : values) {
            builder.append(value).append('|');
        }
        map.put(key, builder.toString());
    }

    @Override
    public int[] load(String key) {

        String value = map.get(key);
        String[] values = value.split("\\|");

        int[] array = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            array[i] = Integer.parseInt(values[i]);
        }

        return array;
    }
}
