package sample;

import prime.sieve.SieveOfEratosthenes;

public class SieveOfEratosthenesSample {

    public static void main(String[] args) {


        // Prime numbers
        //   10:   29
        //  100:  541
        // 1000: 7919
        int[] primes = SieveOfEratosthenes.findPrimes(1000, 20);
        System.out.printf("Primes:%n");
        for (int i = 0; i < primes.length; i++) {
            System.out.printf("prime[%d]=%d%n", i, primes[i]);
        }
    }
}
