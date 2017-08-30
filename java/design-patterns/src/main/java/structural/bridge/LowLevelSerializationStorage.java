package structural.bridge;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LowLevelSerializationStorage implements LowLevelStorage {

    private final Map<String, byte[]> map = new HashMap<>();

    @Override
    public void save(String key, int... values) {

        try (
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os)
        ) {
            oos.writeObject(values);
            map.put(key, os.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int[] load(String key) {

        try (ByteArrayInputStream is = new ByteArrayInputStream(map.get(key));
             ObjectInputStream ois = new ObjectInputStream(is)) {

            return (int[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
