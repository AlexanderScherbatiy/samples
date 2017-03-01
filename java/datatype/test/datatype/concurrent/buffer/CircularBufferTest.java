package datatype.concurrent.buffer;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by alexsch on 3/1/2017.
 */
public class CircularBufferTest {

    public static void main(String[] args) throws Exception {
        testBuffer(new CircularBufferIntrinsicLock<Integer>(8));
    }

    static void testBuffer(final CircularBuffer<Integer> buff) throws Exception {


        final CountDownLatch latch = new CountDownLatch(1);

        Random rand = new Random();

        final int N = 10000;
        final int[] GOLDEN = new int[N];
        final int[] RESULT = new int[N];
        for (int i = 0; i < N; i++) {
            GOLDEN[i] = rand.nextInt(N * 1000);
        }

        Thread producer = new Thread(() -> {
            int[] from = Arrays.copyOf(GOLDEN, N);
            try {
                latch.await();
                for (int i = 0; i < N; i++) {
                    buff.put(from[i]);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread consumer = new Thread(() -> {
            int[] to = new int[N];
            try {
                latch.await();
                for (int i = 0; i < N; i++) {
                    to[i] = buff.take();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (int i = 0; i < N; i++) {
                RESULT[i] = to[i];
            }
        });

        producer.start();
        consumer.start();
        latch.countDown();
        producer.join(1000);
        consumer.join(1000);

        if (!Arrays.equals(GOLDEN, RESULT)) {
            throw new RuntimeException("Producer/Consumer results are different!");
        }
    }

}
