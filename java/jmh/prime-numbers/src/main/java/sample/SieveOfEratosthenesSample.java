package sample;

import prime.sieve.SieveOfEratosthenesLongArray;

public class SieveOfEratosthenesSample {

    public static void main(String[] args) {

        // Prime numbers
        //    10:     29
        //   100:    541
        //  1000:   7919
        // 10000: 104729

        int primesSize = 1_000;
        int N = 5;

        if (args.length > 0) {
            try {
                primesSize = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.printf("Provided primes size argument is not parsed as int: %s%n", args[0]);
                System.exit(-1);
            }
        }

        long time = System.nanoTime();
        long[] primes = SieveOfEratosthenesLongArray.findPrimes(primesSize, 10000);
        time = System.nanoTime() - time;

        System.out.printf("Last %d prime numbers:%n", N);
        for (int i = Math.max(primesSize - N, 0); i < primesSize; i++) {
            System.out.printf("Prime number[%d]: %d%n", i + 1, primes[i]);
        }

        System.out.printf("Elapsed time: %.3f s%n", time / 1e9);
    }
}
