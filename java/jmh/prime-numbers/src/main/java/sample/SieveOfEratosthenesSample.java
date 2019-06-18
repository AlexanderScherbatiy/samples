package sample;

import prime.sieve.SieveOfEratosthenesIntArray;

public class SieveOfEratosthenesSample {

    public static void main(String[] args) {


        // Prime numbers
        //   10:   29
        //  100:  541
        // 1000: 7919


        int primesSize = 1000;

        if (args.length > 0) {
            try {
                primesSize = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.printf("Provided primes size argument is not parsed as int: %s%n", args[0]);
                System.exit(-1);
            }
        }

        long time = System.nanoTime();
        int[] primes = SieveOfEratosthenesIntArray.findPrimes(primesSize, 10000);
        time = System.nanoTime() - time;
        System.out.printf("last prime[%d]: %d%n", primesSize, primes[primesSize - 1]);
        System.out.printf("Elapsed time: %.3f s%n", time / 1e9);
    }
}
