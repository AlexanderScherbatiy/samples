package datatype.concurrent.executor;

/**
 * Created by alexsch on 4/26/2017.
 */
public class TimerExecutorTest {

    private static final long[] TIMEOUTS = {
            6_000,
            10_000,
            3_000,
            1_000,
            7_000,
            4_000,
            5_000,
            2_000
    };

    public static void main(String[] args) {

        final TimerExecutor timerExecutor = new TimerExecutor();

        for (final long timeout : TIMEOUTS) {
            timerExecutor.execute(timeout, () -> {
                System.out.printf("*** Timeout: %2d seconds\n", timeout / 1000);
                if (timeout == 10_000) {
                    timerExecutor.shutdown();
                }
            });
        }
    }
}