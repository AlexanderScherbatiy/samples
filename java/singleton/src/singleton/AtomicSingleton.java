package singleton;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author alexsch
 */
public class AtomicSingleton {

    private static final int S0 = 0;
    private static final int S1 = 1;
    private static final int S2 = 2;
    private static final AtomicInteger STATE = new AtomicInteger(S0);

    private static AtomicSingleton INSTANCE;

    public static AtomicSingleton getInstance() {

        while (STATE.get() != S2) {
            if (STATE.compareAndSet(S0, S1)) {
                INSTANCE = new AtomicSingleton();
                STATE.set(S2);
            }
        }
        return INSTANCE;
    }
}
