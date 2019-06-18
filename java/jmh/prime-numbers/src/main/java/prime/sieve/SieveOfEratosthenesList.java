package prime.sieve;

public class SieveOfEratosthenesList {

    public static class PrimeNode {
        public final int prime;
        public final PrimeNode next;
        private int nextIndex;

        public PrimeNode(int prime, PrimeNode next) {
            this.prime = prime;
            this.next = next;
            nextIndex = prime << 1;
        }
    }

    public static PrimeNode findPrimes(int primesSize, int sieveSize) {

        int maxPrimeIndex = primesSize - 1;

        PrimeNode head = new PrimeNode(2, null);
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

            PrimeNode node = head;

            while (node != null) {
                // Mark Loop: Begin
                int prime = node.prime;
                int nextIndex = node.nextIndex;

                while (nextIndex < maxIndex) {
                    marked[nextIndex - baseIndex] = true;
                    nextIndex += prime;
                }

                node.nextIndex = nextIndex;
                node = node.next;
                // Mark Loop: End
            }

            for (int i = 0; i < sieveSize; i++) {
                if (!marked[i]) {

                    int prime = baseIndex + i;
                    head = new PrimeNode(prime, head);

                    if (primesCount == maxPrimeIndex) {
                        return head;
                    }

                    // Mark Loop: Begin
                    int nextIndex = head.nextIndex;

                    while (nextIndex < maxIndex) {
                        marked[nextIndex - baseIndex] = true;
                        nextIndex += prime;
                    }

                    head.nextIndex = nextIndex;
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

        final int primeSize = 1000;
        PrimeNode node = findPrimes(primeSize, 100);
        System.out.printf("Primes:%n");

        int i = primeSize;
        while (node != null) {
            System.out.printf("prime[%d]=%d%n", i--, node.prime);
            node = node.next;
        }
    }
}