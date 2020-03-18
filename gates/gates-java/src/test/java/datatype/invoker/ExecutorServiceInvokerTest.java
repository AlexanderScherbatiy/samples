package datatype.invoker;

import datatype.ExecutorServiceInvoker;
import datatype.Invoker;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceInvokerTest extends InvokerTest {

    private static Invoker invoker;
    private static ExecutorService executorService;

    @BeforeClass
    public static void beforeClass() {
        executorService = Executors.newCachedThreadPool();
        invoker = new ExecutorServiceInvoker(executorService);
    }

    @AfterClass
    public static void afterClass() {
        executorService.shutdown();
        executorService = null;
        invoker = null;
    }

    @Override
    protected Invoker getInvoker() {
        return invoker;
    }
}
