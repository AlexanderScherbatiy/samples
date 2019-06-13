package prime.sieve;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode({Mode.SampleTime, Mode.AverageTime, Mode.Throughput, Mode.SingleShotTime})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class SieveOfEratosthenesBenchmark {

    @Param({"10", "100", "1000", "10000", "100000"})
    int sieveSize;

    @Benchmark
    public int[] testPrimes() {
        return SieveOfEratosthenes.findPrimes(10_000, sieveSize);
    }


    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()

                .include(SieveOfEratosthenesBenchmark.class.getSimpleName())
                // Use this to selectively constrain/override parameters
                .param("sieveSize", "1000", "10000", "100000")
                .build();

        new Runner(opt).run();
    }
}