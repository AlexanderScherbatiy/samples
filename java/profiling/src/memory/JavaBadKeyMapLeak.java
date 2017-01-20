package memory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by alexsch.
 */
public class JavaBadKeyMapLeak {

    private static final int ITERATIONS = 100000;
    private static final int ARRAY_SIZE = 100000;
    private static final int KEY_VALUE = 42;
    static final Random random = new Random();
    final Map<GoodKey, int[]> goodKeyMap = new HashMap<>();
    final Map<BadKey, int[]> badKeyMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        JavaBadKeyMapLeak test = new JavaBadKeyMapLeak();

        int iterations = ITERATIONS;
        if (args.length > 0) {
            iterations = Integer.parseInt(args[0]);
        }

        int arraySize = ARRAY_SIZE;
        if (args.length > 1) {
            arraySize = Integer.parseInt(args[1]);
        }

        System.out.printf("iterations: %d, array size: %d\n", iterations, arraySize);

        for (int i = 0; i < iterations; i++) {
            test.fillArray(arraySize);
            Thread.sleep(1);
        }
        System.out.printf("Done!\n");
    }

    void fillArray(int arraySize) {
        int n = random.nextInt(arraySize / 2) + arraySize / 2;
        int[] arr = new int[n];
        Arrays.fill(arr, 0, n - 1, n / 2);
        goodKeyMap.put(new GoodKey(KEY_VALUE), arr);
        badKeyMap.put(new BadKey(KEY_VALUE), arr);
    }


    static class GoodKey {

        private final int keyValue;

        public GoodKey(int keyValue) {
            this.keyValue = keyValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GoodKey)) return false;

            GoodKey goodKey = (GoodKey) o;
            return keyValue == goodKey.keyValue;
        }

        @Override
        public int hashCode() {
            return keyValue;
        }
    }

    static class BadKey {

        private final int keyValue;

        public BadKey(int keyValue) {
            this.keyValue = keyValue;
        }
    }
}
