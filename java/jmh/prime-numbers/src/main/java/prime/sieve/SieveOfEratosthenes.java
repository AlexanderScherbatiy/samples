package prime.sieve;

public class SieveOfEratosthenes {

    public static int[] findPrimes(int primesSize, int sieveSize) {

        int maxPrimeIndex = primesSize - 1;

        int[] primes = new int[primesSize];
        int[] lastIndices = new int[primesSize];
        primes[0] = 2;
        lastIndices[0] = 4;
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
                int nextIndex = lastIndices[i];

                while (nextIndex < maxIndex) {
                    marked[nextIndex - baseIndex] = true;
                    nextIndex += prime;
                }

                lastIndices[i] = nextIndex;
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

                    lastIndices[primesCount] = nextIndex;
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
}