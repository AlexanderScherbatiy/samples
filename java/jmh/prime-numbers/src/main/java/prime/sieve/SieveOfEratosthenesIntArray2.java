package prime.sieve;

public class SieveOfEratosthenesIntArray2 {


    // Prime numbers and next indices are stored in the same array.
    // index of prime = 2 * i
    // index of next index = 2 * i + 1
    public static int[] findPrimes(int primesSize, int sieveSize) {

        int maxPrimeIndex = primesSize - 1;

        int[] primesWithIndices = new int[primesSize << 1];
        primesWithIndices[0] = 2;
        primesWithIndices[1] = 4;
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
                int index = i << 1;
                int prime = primesWithIndices[index];
                int nextIndex = primesWithIndices[index + 1];

                while (nextIndex < maxIndex) {
                    marked[nextIndex - baseIndex] = true;
                    nextIndex += prime;
                }

                primesWithIndices[index + 1] = nextIndex;
                // Mark Loop: End
            }


            for (int i = 0; i < sieveSize; i++) {
                if (!marked[i]) {

                    int index = primesCount << 1;
                    int prime = baseIndex + i;
                    primesWithIndices[index] = prime;

                    if (primesCount == maxPrimeIndex) {
                        return primesWithIndices;
                    }

                    // Mark Loop: Begin
                    int nextIndex = prime << 1;

                    while (nextIndex < maxIndex) {
                        marked[nextIndex - baseIndex] = true;
                        nextIndex += prime;
                    }

                    primesWithIndices[index + 1] = nextIndex;
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
        int primesSize = 10_000;
        int[] primes = findPrimes(primesSize, 100);
        System.out.printf("Primes:%n");
        for (int i = 0; i < primesSize; i++) {
            System.out.printf("prime[%d]=%d%n", i + 1, primes[i << 1]);
        }
    }
}