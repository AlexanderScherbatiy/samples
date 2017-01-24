package memory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by alexsch.
 */
public class StringInternLeak {

    private static final int N = 1000000;
    private static final int MAX_STRING_LENGTH = 100000000;
    private static final Random random = new Random();

    public static void main(String[] args) {

        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String s = generateString();
            map.put(i, s);
        }
    }

    private static String generateString() {
        char[] buff = new char[MAX_STRING_LENGTH];
        for (int i = 0; i < MAX_STRING_LENGTH; i++) {
            buff[i] = (char) (random.nextInt(10) + '0');
        }
        return new String(buff).intern();
    }
}
