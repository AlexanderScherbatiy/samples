package prime.sieve;

public class SieveOfEratosthenesIntArrayMarkCounter {

    // Marked counter is used to skip iterating through the sieve
    // in case all cells are marked.
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
        int markedCounter = sieveSize;
        marked[0] = true;
        marked[1] = true;
        markedCounter -= 2;

        boolean markedValue = true;


        while (true) {

            for (int i = 0; i < primesCount; i++) {
                // Mark Loop: Begin
                int prime = primes[i];
                int nextIndex = nextIndices[i];

                while (nextIndex < maxIndex) {
                    int markIndex = nextIndex - baseIndex;
                    if (marked[markIndex] != markedValue) {
                        marked[markIndex] = markedValue;
                        markedCounter--;
                    }
                    nextIndex += prime;
                }

                nextIndices[i] = nextIndex;
                // Mark Loop: End
            }

            if (markedCounter == 0) {
                markedCounter = sieveSize;
                continue;
            }

            for (int i = 0; i < sieveSize; i++) {
                if (marked[i] != markedValue) {

                    marked[i] = markedValue;
                    markedCounter--;

                    int prime = baseIndex + i;
                    primes[primesCount] = prime;

                    if (primesCount == maxPrimeIndex) {
                        return primes;
                    }

                    // Mark Loop: Begin
                    int nextIndex = prime << 1;

                    while (nextIndex < maxIndex) {
                        int markIndex = nextIndex - baseIndex;
                        if (marked[markIndex] != markedValue) {
                            marked[markIndex] = markedValue;
                            markedCounter--;
                        }
                        nextIndex += prime;
                    }

                    nextIndices[primesCount] = nextIndex;
                    // Mark Loop: End

                    primesCount++;

                    if (markedCounter == 0) {
                        break;
                    }
                }
            }

            baseIndex = maxIndex;
            maxIndex += sieveSize;

            markedValue = !markedValue;
            markedCounter = sieveSize;
        }
    }

    public static void main(String[] args) {
        int primesSize = 10_000;
        int[] primes = findPrimes(primesSize, 10);
        System.out.printf("Primes:%n");
        for (int i = 0; i < primesSize; i++) {
            System.out.printf("prime[%d]=%d%n", i + 1, primes[i]);
        }
    }
}