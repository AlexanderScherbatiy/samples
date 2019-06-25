package prime.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class StoreIndicesAndValuesBenchmark {

    private static final int SIEVE_SIZE = 10_000;

    @Param({"10000", "100000", "1000000",})
    int valuesNum;

    int initialIndexMask;
    int initialValueMask;
    int[] indices;
    int[] values;

    int[] valuesWithIndices1;
    int[] valuesWithIndices2;

    @Setup(Level.Trial)
    public void setup() {
        initialIndexMask = ThreadLocalRandom.current().nextInt();
        initialIndexMask = ThreadLocalRandom.current().nextInt();
        indices = new int[valuesNum];
        values = new int[valuesNum];
        valuesWithIndices1 = new int[valuesNum << 1];
        valuesWithIndices2 = new int[valuesNum << 1];

        for (int i = 0; i < valuesNum; i++) {
            int index = initialIndexMask & i;
            int value = initialValueMask & i;
            indices[i] = index;
            values[i] = value;

            valuesWithIndices1[i] = value;
            valuesWithIndices1[i + valuesNum] = index;

            valuesWithIndices2[i << 1] = value;
            valuesWithIndices2[(i << 1) + 1] = index;
        }
    }

    @Benchmark
    public int storeInDifferentArrays() {
        int result = 0xFFFFFFFF;

        for (int i = 0; i < valuesNum; i++) {
            result = result & indices[i];
            result = result & values[i];
        }

        return result;
    }

    @Benchmark
    public int storeInTheSameArray1() {
        int result = 0xFFFFFFFF;

        for (int i = 0; i < valuesNum; i++) {
            result = result & valuesWithIndices1[i];
            result = result & valuesWithIndices1[i + valuesNum];
        }

        return result;
    }

    @Benchmark
    public int storeInTheSameArray2() {
        int result = 0xFFFFFFFF;

        for (int i = 0; i < valuesNum; i++) {
            int index = i << 1;
            result = result & valuesWithIndices1[index];
            result = result & valuesWithIndices1[index + 1];
        }

        return result;
    }

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()

                .include(StoreIndicesAndValuesBenchmark.class.getSimpleName())
                // Use this to selectively constrain/override parameters
                .param("valuesNum", "1000", "10000", "100000")
                .build();

        new Runner(opt).run();
    }
}