package memory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by alexsch.
 */
public class JavaHeapLeak {

    static final int N = 100000000;
    static final int HALF_MAX = 10_000 / 2;
    static final Random random = new Random();
    final Map<Integer, int[]> cleanedMap = new HashMap<>();
    final Map<Integer, int[]> leakedMap = new HashMap<>();

    public static void main(String[] args) {
        JavaHeapLeak test = new JavaHeapLeak();
        test.run();
    }

        for (int i = 0; i < N; i++) {
            cleanedMap.clear();
    }
}
