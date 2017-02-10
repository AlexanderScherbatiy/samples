package benchmark;

import java.util.Objects;
import singleton.AtomicSingleton;
import singleton.HolderSingleton;
import singleton.DoubleCheckedLockingSingleton;
import singleton.SynchronizedMethodSingleton;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 *
 * @author alexsch
 */
public class SingletonBenchmark {

    @Benchmark
    public Object synchronizedMethodSingletonBenchmark() throws InterruptedException {
        return SynchronizedMethodSingleton.getInstance();
    }

    @Benchmark
    public Object doubleCheckedLockingSingletonBenchmark() throws InterruptedException {
        return DoubleCheckedLockingSingleton.getInstance();
    }

    @Benchmark
    public Object holderSingletonBenchmark() throws InterruptedException {
        return HolderSingleton.getInstance();
    }

    @Benchmark
    public Object atomicSingletonBenchmark() throws InterruptedException {
        return AtomicSingleton.getInstance();
    }

    private static void checkSingleton() {
        check(
                SynchronizedMethodSingleton.getInstance(),
                SynchronizedMethodSingleton.getInstance()
        );
        check(
                DoubleCheckedLockingSingleton.getInstance(),
                DoubleCheckedLockingSingleton.getInstance()
        );
        check(
                HolderSingleton.getInstance(),
                HolderSingleton.getInstance()
        );
        check(
                AtomicSingleton.getInstance(),
                AtomicSingleton.getInstance()
        );
    }

    private static void check(Object singleton1, Object singleton2) {
        Objects.requireNonNull(singleton1);
        Objects.requireNonNull(singleton1);
        if (singleton1 != singleton2) {
            throw new RuntimeException("Singleton is not unique!");
        }
    }

    public static void main(String[] args) throws Exception {

        Options opt = new OptionsBuilder()
                .include(SingletonBenchmark.class.getSimpleName())
                .forks(1)
                .threads(4)
                .build();

        new Runner(opt).run();
    }
}
