package gate;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Supplier;

public class WithTimeoutTest {

    @Test
    public void test() {
        System.out.printf("Hello, Test!");

        Integer result = withTimeout(1000, WithTimeoutTest::funcA);
        Assert.assertEquals(1, result.intValue());
    }


    private static Integer funcA() {
        delay(10);
        return 1;
    }

    private static <T> T withTimeout(long timeout, Supplier<T> supplier) {
        return supplier.get();
    }

    private static void delay(long waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException ignore) {
        }
    }
}
