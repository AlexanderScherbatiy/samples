package singleton;

/**
 *
 * @author alexsch
 */
public class SynchronizedMethodSingleton {

    private static SynchronizedMethodSingleton INSTANCE;

    public static synchronized SynchronizedMethodSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SynchronizedMethodSingleton();
        }
        return INSTANCE;
    }
}
