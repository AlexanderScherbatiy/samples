package structural.bridge;

import org.junit.Test;

import static org.junit.Assert.*;

public class BridgePatternTest {

    @Test
    public void testLowLevelStringStorage() {
        testLowLeveStorage(new LowLevelStringStorage());
    }

    @Test
    public void testLowLevelSerializationStorage() {
        testLowLeveStorage(new LowLevelSerializationStorage());
    }

    @Test
    public void testStringStorage() {
        testStorage(new LowLevelStringStorage());
    }

    @Test
    public void testSerializationStorage() {
        testStorage(new LowLevelStringStorage());
    }

    public void testLowLeveStorage(LowLevelStorage lowLevelStorage) {
        lowLevelStorage.save("arr", 1, 2, 3);
        int[] arr = lowLevelStorage.load("arr");
        assertArrayEquals(arr, new int[]{1, 2, 3});
    }

    public void testStorage(LowLevelStorage lowLevelStorage) {

        Storage storage = new StorageImpl(lowLevelStorage);
        storage.save("arr", 5, 10, 20);

        int[] arr = storage.load("arr");
        assertArrayEquals(new int[]{5, 10, 20}, arr);
    }
}
