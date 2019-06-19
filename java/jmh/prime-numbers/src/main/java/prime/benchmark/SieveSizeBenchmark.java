package prime.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import prime.sieve.SieveOfEratosthenesIntArray;
import prime.sieve.SieveOfEratosthenesIntBuffer;
import prime.sieve.SieveOfEratosthenesItemArray;
import prime.sieve.SieveOfEratosthenesList;

import java.nio.IntBuffer;
import java.util.concurrent.TimeUnit;

import static prime.sieve.SieveOfEratosthenesList.PrimeNode;
import static prime.sieve.SieveOfEratosthenesItemArray.PrimeItem;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class SieveSizeBenchmark {

    private static final int PRIMES_NUM = 10_000;

    @Param({"100", "1000", "10000", "100000", "1000000"})
    int sieveSize;

    @Benchmark
    public int[] testPrimesIntArray() {
        return SieveOfEratosthenesIntArray.findPrimes(PRIMES_NUM, sieveSize);
    }

    @Benchmark
    public IntBuffer testPrimesIntBuffer() {
        return SieveOfEratosthenesIntBuffer.findPrimes(PRIMES_NUM, sieveSize);
    }

    @Benchmark
    public PrimeItem[] testPrimesItemArray() {
        return SieveOfEratosthenesItemArray.findPrimes(PRIMES_NUM, sieveSize);
    }

    @Benchmark
    public PrimeNode testPrimesList() {
        return SieveOfEratosthenesList.findPrimes(PRIMES_NUM, sieveSize);
    }

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()

                .include(SieveSizeBenchmark.class.getSimpleName())
                // Use this to selectively constrain/override parameters
                .param("sieveSize", "1000", "10000", "100000")
                .build();

        new Runner(opt).run();
    }
}