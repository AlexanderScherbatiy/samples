package datatype;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class ExecutorServiceInvoker implements Invoker {

    private final ExecutorService executorService;

    public ExecutorServiceInvoker(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public <T> T callWithTimeout(long timeout, T defaultValue, Supplier<T> supplier) {

        try {
            Future<T> future = executorService.submit(() -> supplier.get());
            return future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            return defaultValue;
        }
    }
}
