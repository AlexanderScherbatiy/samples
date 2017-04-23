package sample.java.util.concurrent.locks;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.StampedLock;

/**
 * Created by alexsch on 4/23/2017.
 */
public class PointLockSample {


    public static void main(String[] args) throws Exception {

        int STEPS = 100000;
        testPoint(new SyncPoint(1, 0), STEPS);
        testPoint(new StampedPoint(1, 0), STEPS);
    }

    private static void testPoint(final Point point, final int STEPS) throws Exception {

        final CountDownLatch latch = new CountDownLatch(1);

        Thread writeThread = new Thread(() -> {

            try {

                double angle = Math.PI / 16;
                double cos = Math.cos(angle);
                double sin = Math.sin(angle);
                latch.await();

                for (int i = 0; i < STEPS; i++) {
                    Point copy = point.getCopy();
                    double x = copy.getX();
                    double y = copy.getY();

                    double xx = x * cos - y * sin;
                    double yy = x * sin + y * cos;
                    point.set(xx, yy);
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread readThread = new Thread(() -> {

            try {
                latch.await();

                for (int i = 0; i < STEPS; i++) {

                    double dist = point.getDistance();

                    if (Math.abs(1 - dist) > 0.01) {
                        throw new RuntimeException(
                                String.format("Wrong distance %f, iteration: %d, point: %s",
                                        dist, i, point.getClass().getSimpleName()));
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        writeThread.start();
        readThread.start();
        latch.countDown();

        writeThread.join();
        readThread.join();
    }
}
