package datatype;

import java.util.function.Supplier;

public class SimpleInvoker implements Invoker {

    @Override
    public <T> T callWithTimeout(long timeout, T defaultValue, Supplier<T> supplier) {

        RunnableWithResult<T> r = new RunnableWithResult(defaultValue, supplier);
        Thread thread = new Thread(r);
        thread.start();

        try {
            thread.join(timeout);
            return r.result;
        } catch (InterruptedException e) {
            return defaultValue;
        }
    }

    private static class RunnableWithResult<T> implements Runnable {

        volatile T result;
        final Supplier<T> supplier;

        RunnableWithResult(T defaultValue, Supplier<T> supplier) {
            this.result = defaultValue;
            this.supplier = supplier;
        }

        @Override
        public void run() {
            result = supplier.get();
        }
    }
}
