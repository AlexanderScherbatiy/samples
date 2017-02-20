package datatype.concurrent;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by alexsch.
 */
public class BlockingValueTest {

    private static final int N = 5;
    private static final int MAX_DELAY = 100;

    private static volatile String[] SRC;
    private static volatile String[] DST;

    public static void main(String[] args) throws Exception {

        final BlockingValue<String> blockingValue = new BlockingValue<>();
        final CountDownLatch latch = new CountDownLatch(1);

        Thread producer = new Thread(() -> {
            // writer
            Random random = new Random();
            String name = Thread.currentThread().getName();
            String[] src = new String[N];

            for (int i = 0; i < src.length; i++) {
                src[i] = random.nextInt(N * 100) + 1 + "";
            }

            try {
                latch.await();
                for (int i = 0; i < N; i++) {
                    Thread.sleep(random.nextInt(MAX_DELAY));
                    String value = src[i];
                    //System.out.printf("thread: %s try set value.\n", name, value);
                    blockingValue.setValue(value);
                    System.out.printf("thread: %s set value[%d]: %s\n", name, i, value);
                }
                SRC = src;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }, "[writer]");

        Thread consumer = new Thread(() -> {
            Random random = new Random();

            String[] dst = new String[N];

            String name = Thread.currentThread().getName();
            try {
                latch.await();
                for (int i = 0; i < N; i++) {
                    Thread.sleep(random.nextInt(MAX_DELAY));
                    System.out.printf("thread: %s wait for a value[%d].\n", name, i);
                    dst[i] = blockingValue.getValue();
                    System.out.printf("thread: %s get value[%d]: %s\n", name, i, dst[i]);
                }
                DST = dst;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }, "[reader]");

        consumer.start();
        producer.start();
        latch.countDown();

        consumer.join(MAX_DELAY * N + 100);
        producer.join(MAX_DELAY * N + 100);

        Objects.requireNonNull(SRC);
        Objects.requireNonNull(DST);
        for (int i = 0; i < N; i++) {
            Objects.requireNonNull(SRC[i]);
            Objects.requireNonNull(DST[i]);
            if (!Objects.equals(SRC[i], DST[i])) {
                throw new RuntimeException(String.format("Produced: %s, consumed: %s", SRC[i], DST[i]));
            }
        }
    }
}