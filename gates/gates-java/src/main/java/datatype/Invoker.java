package datatype;

import java.util.function.Supplier;

public interface Invoker {

    <T>  T callWithTimeout(long timeout, T defaultValue, Supplier<T> supplier);
}
