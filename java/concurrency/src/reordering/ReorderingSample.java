package reordering;

/**
 * Created by alexsch.
 */
public class ReorderingSample {

    private static final int THREADS = 1000000;
    private static volatile boolean interrupted = false;

    public static void main(String[] args) throws Exception {

        final int threads = args.length > 0 ? Integer.parseInt(args[0]) : THREADS;
        System.out.printf("Threads: %d\n", threads);

        for (int i = 0; i < threads; i++) {

            if (interrupted) {
                break;
            }

            final int iteration = i;

            new Thread(() -> {

                try {
                    final Test test = new Test();
                    Thread t1 = new Thread(test::update1);
                    Thread t2 = new Thread(test::update2);
                    t1.start();
                    t2.start();
                    t1.join();
                    t2.join();
                    if (test.value1 == 1 && test.value2 == 1) {
                        interrupted = true;
                        throw new RuntimeException(
                                String.format("Reordering iteration: %d of %d, %s",
                                        iteration, threads, test));
                    }
                } catch (InterruptedException e) {
                    interrupted = true;
                    throw new RuntimeException(e);
                }

            }).start();
        }

    }

    static class Test {
        boolean flag1 = false;
        boolean flag2 = false;

        int value1 = -1;
        int value2 = -1;

        void update1() {
            flag1 = true;
            value2 = flag2 ? 0 : 1;
        }

        void update2() {
            flag2 = true;
            value1 = flag1 ? 0 : 1;
        }

        @Override
        public String toString() {
            return String.format("value1: %d, value2: %d", value1, value2);
        }
    }
}
