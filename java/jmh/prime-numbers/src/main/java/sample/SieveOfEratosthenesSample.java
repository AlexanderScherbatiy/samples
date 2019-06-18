package sample;

import prime.sieve.SieveOfEratosthenesIntArray;

public class SieveOfEratosthenesSample {

    public static void main(String[] args) {


        // Prime numbers
        //   10:   29
        //  100:  541
        // 1000: 7919

        int[] primes = SieveOfEratosthenesIntArray.findPrimes(1000000, 100);
        System.out.printf("Primes:%n");
        for (int i = 0; i < primes.length; i++) {
            System.out.printf("prime[%d]=%d%n", i + 1, primes[i]);
        }
    }
}
