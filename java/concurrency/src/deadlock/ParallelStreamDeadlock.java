package deadlock;

import java.util.stream.IntStream;

/**
 * Created by alexsch.
 */
public class ParallelStreamDeadlock {

    // See option -Djava.util.concurrent.ForkJoinPool.common.parallelism=N
    static final int SUM = IntStream.range(0, 100).parallel().reduce((n, m) -> n + m).getAsInt();

    public static void main(String[] args) {
        System.out.println(SUM);
    }
}
