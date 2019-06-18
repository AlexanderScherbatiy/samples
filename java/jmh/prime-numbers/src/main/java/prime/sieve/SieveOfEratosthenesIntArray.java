package prime.sieve;

public class SieveOfEratosthenesIntArray {

    public static int[] findPrimes(int primesSize, int sieveSize) {

        int maxPrimeIndex = primesSize - 1;

        int[] primes = new int[primesSize];
        int[] nextIndices = new int[primesSize];
        primes[0] = 2;
        nextIndices[0] = 4;
        int primesCount = 1;


        int baseIndex = 0;
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
                int prime = primes[i];
                int nextIndex = nextIndices[i];

                while (nextIndex < maxIndex) {
                    marked[nextIndex - baseIndex] = true;
                    nextIndex += prime;
                }

                nextIndices[i] = nextIndex;
                // Mark Loop: End
            }


            for (int i = 0; i < sieveSize; i++) {
                if (!marked[i]) {

                    int prime = baseIndex + i;
                    primes[primesCount] = prime;

                    if (primesCount == maxPrimeIndex) {
                        return primes;
                    }

                    // Mark Loop: Begin
                    int nextIndex = prime + prime;

                    while (nextIndex < maxIndex) {
                        marked[nextIndex - baseIndex] = true;
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
        int[] primes = findPrimes(1000, 100);
        System.out.printf("Primes:%n");
        for (int i = 0; i < primes.length; i++) {
            System.out.printf("prime[%d]=%d%n", i, primes[i]);
        }
    }
}