package prime.sieve;

public class SieveOfEratosthenesItemArray {

    public static class PrimeItem {
        public final int prime;
        private int nextIndex;

        public PrimeItem(int prime) {
            this.prime = prime;
            nextIndex = prime << 1;
        }
    }

    public static PrimeItem[] findPrimes(int primesSize, int sieveSize) {

        int maxPrimeIndex = primesSize - 1;

        PrimeItem[] primes = new PrimeItem[primesSize];
        primes[0] = new PrimeItem(2);
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
                PrimeItem item = primes[i];
                int prime = item.prime;
                int nextIndex = item.nextIndex;

                while (nextIndex < maxIndex) {
                    marked[nextIndex - baseIndex] = true;
                    nextIndex += prime;
                }

                item.nextIndex = nextIndex;
                // Mark Loop: End
            }


            for (int i = 0; i < sieveSize; i++) {
                if (!marked[i]) {

                    int prime = baseIndex + i;
                    PrimeItem item = new PrimeItem(prime);
                    primes[primesCount] = item;

                    if (primesCount == maxPrimeIndex) {
                        return primes;
                    }

                    // Mark Loop: Begin
                    int nextIndex = prime << 1;

                    while (nextIndex < maxIndex) {
                        marked[nextIndex - baseIndex] = true;
                        nextIndex += prime;
                    }

                    item.nextIndex = nextIndex;
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
        PrimeItem[] primes = findPrimes(1000, 100);
        System.out.printf("Primes:%n");
        for (int i = 0; i < primes.length; i++) {
            System.out.printf("prime[%d]=%d%n", i + 1, primes[i].prime);
        }
    }
}