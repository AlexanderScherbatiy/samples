package benchmark;

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

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SingletonBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
