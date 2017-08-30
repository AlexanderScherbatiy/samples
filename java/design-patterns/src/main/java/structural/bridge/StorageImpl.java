package structural.bridge;

public class StorageImpl implements Storage {

    private final LowLevelStorage lowLevelStorage;

    public StorageImpl(LowLevelStorage lowLevelStorage) {
        this.lowLevelStorage = lowLevelStorage;
    }

    @Override
    public void save(String key, int... values) {
        lowLevelStorage.save(key, values);
    }

    @Override
    public int[] load(String key) {
        return lowLevelStorage.load(key);
    }
}
