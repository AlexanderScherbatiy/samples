package prime.sieve;

public class SieveOfEratosthenesIntArray3 {


    // Prime numbers and next indices are stored in the same array.
    // index of prime = i
    // index of next index = i + primesSize
    public static int[] findPrimes(int primesSize, int sieveSize) {

        int maxPrimeIndex = primesSize - 1;

        int[] primesWithIndices = new int[primesSize << 1];
        primesWithIndices[0] = 2;
        primesWithIndices[primesSize] = 4;
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

                int prime = primesWithIndices[i];
                int nextIndex = primesWithIndices[i + primesSize];

                while (nextIndex < maxIndex) {
                    marked[nextIndex - baseIndex] = true;
                    nextIndex += prime;
                }

                primesWithIndices[i + primesSize] = nextIndex;
                // Mark Loop: End
            }


            for (int i = 0; i < sieveSize; i++) {
                if (!marked[i]) {


                    int prime = baseIndex + i;
                    primesWithIndices[primesCount] = prime;

                    if (primesCount == maxPrimeIndex) {
                        return primesWithIndices;
                    }

                    // Mark Loop: Begin
                    int nextIndex = prime << 1;

                    while (nextIndex < maxIndex) {
                        marked[nextIndex - baseIndex] = true;
                        nextIndex += prime;
                    }

                    primesWithIndices[primesCount + primesSize] = nextIndex;
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
            System.out.printf("prime[%d]=%d%n", i + 1, primes[i]);
        }
    }
}