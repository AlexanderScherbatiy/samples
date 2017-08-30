package structural.bridge;

public interface Storage {

    void save(String key, int... values);

    int[] load(String key);
}
