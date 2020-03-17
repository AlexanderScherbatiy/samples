package gate;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Supplier;

public class WithTimeoutTest {

    @Test
    public void testReturnResult() {

        int res = withTimeout(1000, -1, () -> {
            delay(10);
            return 1;
        });

        Assert.assertEquals(1, res);
    }

    @Test
    public void testTimeout() {

        int res = withTimeout(10, -1, () -> {
            delay(1000);
            return 2;
        });

        Assert.assertEquals(-1, res);
    }

    @Test
    public void testInfinityLoop() {

        int res = withTimeout(20, -5, () -> {
            while (true) ;
        });

        Assert.assertEquals(-5, res);
    }

    private static <T> T withTimeout(long timeout, T defaultValue, Supplier<T> supplier) {

        class RunnableWithResult implements Runnable {

            private volatile T result = defaultValue;

            @Override
            public void run() {
                result = supplier.get();
            }
        }

        RunnableWithResult r = new RunnableWithResult();
        Thread thread = new Thread(r);
        thread.start();

        try {
            thread.join(timeout);
            return r.result;
        } catch (InterruptedException e) {
            return defaultValue;
        }
    }

    private static void delay(long waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException ignore) {
        }
    }
}
