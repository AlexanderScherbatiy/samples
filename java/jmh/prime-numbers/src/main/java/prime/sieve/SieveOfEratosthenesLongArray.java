package prime.sieve;

public class SieveOfEratosthenesLongArray {

    public static long[] findPrimes(int primesSize, int sieveSize) {

        int maxPrimeIndex = primesSize - 1;

        long[] primes = new long[primesSize];
        long[] nextIndices = new long[primesSize];
        primes[0] = 2;
        nextIndices[0] = 4;
        int primesCount = 1;


        long baseIndex = 0;
        int maxIndex = sieveSize;
        boolean marked[] = new boolean[sieveSize];
        marked[0] = true;
        marked[1] = true;
        if (sieveSize > 2) {
            marked[2] = true;
        }

        while (true) {

            for (int i = 0; i < primesCount; i++) {
                // Mark Loop: Begin
                long prime = primes[i];
                long nextIndex = nextIndices[i];

                while (nextIndex < maxIndex) {
                    marked[(int) (nextIndex - baseIndex)] = true;
                    nextIndex += prime;
                }

                nextIndices[i] = nextIndex;
                // Mark Loop: End
            }


            for (int i = 0; i < sieveSize; i++) {
                if (!marked[i]) {

                    long prime = baseIndex + i;
                    primes[primesCount] = prime;

                    if (primesCount == maxPrimeIndex) {
                        return primes;
                    }

                    // Mark Loop: Begin
                    long nextIndex = prime << 1;

                    while (nextIndex < maxIndex) {

                        marked[(int) (nextIndex - baseIndex)] = true;
                        nextIndex += prime;
                    }

                    nextIndices[primesCount] = nextIndex;
                    // Mark Loop: End

                    primesCount++;
                } else {
                    marked[i] = false;
                }
            }

            baseIndex = maxIndex;
            maxIndex += sieveSize;
        }
    }

    public static void main(String[] args) {
        long[] primes = findPrimes(1000, 100);
        System.out.printf("Primes:%n");
        for (int i = 0; i < primes.length; i++) {
            System.out.printf("prime[%d]=%d%n", i, primes[i]);
        }
    }
}