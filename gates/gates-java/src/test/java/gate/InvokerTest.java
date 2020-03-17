package gate;

import datatype.Invoker;
import datatype.SimpleInvoker;
import org.junit.Assert;
import org.junit.Test;

public class InvokerTest {

    Invoker invoker = new SimpleInvoker();

    @Test
    public void testReturnResult() {

        int res = invoker.callWithTimeout(1000, -1, () -> {
            delay(10);
            return 1;
        });

        Assert.assertEquals(1, res);
    }

    @Test
    public void testTimeout() {

        int res = invoker.callWithTimeout(10, -1, () -> {
            delay(1000);
            return 2;
        });

        Assert.assertEquals(-1, res);
    }

    @Test
    public void testInfinityLoop() {

        int res = invoker.callWithTimeout(20, -5, () -> {
            while (true) ;
        });

        Assert.assertEquals(-5, res);
    }

    private static void delay(long waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException ignore) {
        }
    }
}
