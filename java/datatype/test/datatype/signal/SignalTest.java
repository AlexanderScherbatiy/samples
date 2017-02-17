package datatype.signal;

/**
 * Created by alexsch on 1/31/2017.
 */
public class SignalTest {

    public static void main(String[] args) {

        testWordLengthSignal();
        testSumMulSignal();
    }

    private static void testWordLengthSignal() {

        final String HELLO = "Hello";
        final String WORLD = "World!";
        final VarSignal<String> wordSignal = new VarSignal<>(HELLO);

        AbstractSignal<Integer> wordLengthSignal = new AbstractSignal<Integer>(wordSignal) {

            @Override
            protected Integer calculate() {
                return wordSignal.get().length();
            }
        };

        test(
                new SignalResult(wordSignal, HELLO),
                new SignalResult(wordLengthSignal, HELLO.length())
        );

        wordSignal.set(WORLD);

        test(
                new SignalResult(wordSignal, WORLD),
                new SignalResult(wordLengthSignal, WORLD.length())
        );
    }

    private static void testSumMulSignal() {
        final VarSignal<Integer> s1 = new VarSignal<>(1);
        final VarSignal<Integer> s2 = new VarSignal<>(2);

        AbstractSignal<Integer> sum = new AbstractSignal<Integer>(s1, s2) {
            @Override
            protected Integer calculate() {
                return s1.get() + s2.get();
            }
        };

        AbstractSignal<Integer> mul = new AbstractSignal<Integer>(s1, s2) {
            @Override
            protected Integer calculate() {
                return s1.get() * s2.get();
            }
        };

        test(
                new SignalResult(s1, 1),
                new SignalResult(s2, 2),
                new SignalResult(sum, 3),
                new SignalResult(mul, 2)
        );

        s1.set(2);

        test(
                new SignalResult(s1, 2),
                new SignalResult(s2, 2),
                new SignalResult(sum, 4),
                new SignalResult(mul, 4)
        );

        s2.set(3);

        test(
                new SignalResult(s1, 2),
                new SignalResult(s2, 3),
                new SignalResult(sum, 5),
                new SignalResult(mul, 6)
        );
    }

    private static void test(SignalResult... tests) {
        for (SignalResult test : tests) {
            test.test();
        }
    }

    private static class SignalResult<T> {
        final AbstractSignal<T> signal;
        final T result;

        public SignalResult(AbstractSignal signal, T result) {
            this.signal = signal;
            this.result = result;
        }

        public void test() {
            T res = signal.get();
            if (!result.equals(res)) {
                throw new RuntimeException(String.format(
                        "The signal result is '%s' instead of '%s'", res, result));
            }
        }
    }
}
