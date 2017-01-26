package singleton;

/**
 *
 * @author alexsch
 */
public class DoubleCheckedLockingSingleton {

    private static volatile DoubleCheckedLockingSingleton INSTANCE;

    public static DoubleCheckedLockingSingleton getInstance() {

        if (INSTANCE == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return INSTANCE;
    }
}
