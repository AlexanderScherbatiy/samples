package memory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by alexsch.
 */
public class JavaHashMapLeak {

    private static final int ITERATIONS = 100000;
    private static final int ARRAY_SIZE = 100000;
    static final Random random = new Random();
    final Map<Integer, int[]> cleanedMap = new HashMap<>();
    final Map<Integer, int[]> leakedMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        JavaHashMapLeak test = new JavaHashMapLeak();

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
        cleanedMap.clear();
        int n = random.nextInt(arraySize / 2) + arraySize / 2;
        int[] arr = new int[n];
        Arrays.fill(arr, 0, n - 1, n / 2);
        cleanedMap.put(n, arr);
        leakedMap.put(n, arr);
    }
}
