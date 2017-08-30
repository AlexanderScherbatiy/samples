package structural.bridge;

public interface LowLevelStorage {

    void save(String key, int... values);

    int[] load(String key);
}
