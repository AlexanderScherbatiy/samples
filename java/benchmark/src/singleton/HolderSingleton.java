package singleton;

/**
 *
 * @author alexsch
 */
public class HolderSingleton {

    private static class Holder {

        private static final HolderSingleton INSTANCE = new HolderSingleton();
    }

    public static HolderSingleton getInstance() {
        return Holder.INSTANCE;
    }
}
