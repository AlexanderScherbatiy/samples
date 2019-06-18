package prime.sieve;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

import static prime.sieve.SieveOfEratosthenesItemArray.PrimeItem;
import static prime.sieve.SieveOfEratosthenesList.PrimeNode;

@BenchmarkMode({Mode.SampleTime, Mode.AverageTime, Mode.Throughput, Mode.SingleShotTime})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class PrimesSizeBenchmark {

    private static final int SIEVE_SIZE = 10_000;

    @Param({"1000", "10000", "50000", "100000"})
    int primesNum;

    @Benchmark
    public int[] testPrimesIntArray() {
        return SieveOfEratosthenesIntArray.findPrimes(primesNum, SIEVE_SIZE);
    }

    @Benchmark
    public PrimeItem[] testPrimesItemArray() {
        return SieveOfEratosthenesItemArray.findPrimes(primesNum, SIEVE_SIZE);
    }

    @Benchmark
    public PrimeNode testPrimesList() {
        return SieveOfEratosthenesList.findPrimes(primesNum, SIEVE_SIZE);
    }

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()

                .include(PrimesSizeBenchmark.class.getSimpleName())
                // Use this to selectively constrain/override parameters
                .param("primesNum", "1000", "10000", "100000")
                .build();

        new Runner(opt).run();
    }
}