package benchmark;

import java.util.Objects;
import singleton.AtomicSingleton;
import singleton.HolderSingleton;
import singleton.DoubleCheckedLockingSingleton;
import singleton.SynchronizedMethodSingleton;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 *
 * @author alexsch
 */
public class SingletonBenchmark {

    @Benchmark
    public void synchronizedMethodSingletonBenchmark() throws InterruptedException {
        SynchronizedMethodSingleton.getInstance();
    }

    @Benchmark
    public void doubleCheckedLockingSingletonBenchmark() throws InterruptedException {
        DoubleCheckedLockingSingleton.getInstance();
    }

    @Benchmark
    public void holderSingletonBenchmark() throws InterruptedException {
        HolderSingleton.getInstance();
    }

    @Benchmark
    public void atomicSingletonBenchmark() throws InterruptedException {
        AtomicSingleton.getInstance();
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
                .build();

        new Runner(opt).run();
    }
}
