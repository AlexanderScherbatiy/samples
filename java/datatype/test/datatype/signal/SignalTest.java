package datatype.signal;

/**
 * Created by alexsch on 1/31/2017.
 */
public class SignalTest {

    public static void main(String[] args) {
        testSignal();
    }

    private static void testSignal() {

        final String HELLO = "Hello";
        final String WORLD = "World!";
        final VarSignal<String> wordSignal = new VarSignal<>(HELLO);

        AbstractSignal<Integer> wordLengthSignal = new AbstractSignal<Integer>(wordSignal) {

            @Override
            protected Integer calculate() {
                return wordSignal.get().length();
            }
        };

        if (!HELLO.equals(wordSignal.get())) {
            throw new RuntimeException(String.format(
                    "Signal value is '%s' instead of '%s'", wordSignal.get(), HELLO));
        }

        if (HELLO.length() != wordLengthSignal.get()) {
            throw new RuntimeException(String.format(
                    "Signal value is '%d' instead of '%d'", wordLengthSignal.get(), HELLO.length()));
        }

        wordSignal.setValue(WORLD);

        if (!WORLD.equals(wordSignal.get())) {
            throw new RuntimeException(String.format(
                    "Signal value is '%s' instead of '%s'", wordSignal.get(), WORLD));
        }

        if (WORLD.length() != wordLengthSignal.get()) {
            throw new RuntimeException(String.format(
                    "Signal value is '%d' instead of '%d'", wordLengthSignal.get(), WORLD.length()));
        }
    }
}
