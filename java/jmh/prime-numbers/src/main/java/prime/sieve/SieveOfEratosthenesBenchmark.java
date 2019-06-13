package prime.sieve;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
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
}