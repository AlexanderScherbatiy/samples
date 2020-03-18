package datatype.invoker;

import datatype.Invoker;
import datatype.SimpleInvoker;

public class SimpleInvokerTest extends InvokerTest {

    Invoker invoker = new SimpleInvoker();

    @Override
    protected Invoker getInvoker() {
        return invoker;
    }
}
