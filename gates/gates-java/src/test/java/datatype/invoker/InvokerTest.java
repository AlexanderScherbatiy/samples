package datatype.invoker;

import datatype.Invoker;
import org.junit.Assert;
import org.junit.Test;

public abstract class InvokerTest {

    protected abstract Invoker getInvoker();

    @Test
    public void testReturnResult() {

        int res = getInvoker().callWithTimeout(1000, -1, () -> {
            delay(10);
            return 1;
        });

        Assert.assertEquals(1, res);
    }

    @Test
    public void testTimeout() {

        int res = getInvoker().callWithTimeout(10, -1, () -> {
            delay(1000);
            return 2;
        });

        Assert.assertEquals(-1, res);
    }

    @Test
    public void testInfinityLoop() {

        int res = getInvoker().callWithTimeout(20, -5, () -> {
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
