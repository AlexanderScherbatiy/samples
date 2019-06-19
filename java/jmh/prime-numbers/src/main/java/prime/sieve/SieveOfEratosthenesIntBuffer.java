package prime.sieve;

import java.nio.IntBuffer;

public class SieveOfEratosthenesIntBuffer {

    public static IntBuffer findPrimes(int primesSize, int sieveSize) {

        int maxPrimeIndex = primesSize - 1;

        IntBuffer buff = IntBuffer.allocate(primesSize << 1);
        buff.put(0, 2); // add prime
        buff.put(primesSize, 4); // add next index

        int primesCount = 1;

        int baseIndex = 0;
        int maxIndex = sieveSize;
        boolean marked[] = new boolean[sieveSize];
        marked[0] = true;
        marked[1] = true;
        if (sieveSize > 2) {
            marked[2] = true;
        }


        final int MASK = 0xFFFFF;

        while (true) {

            for (int i = 0; i < primesCount; i++) {
                // Mark Loop: Begin
                int prime = buff.get(i);
                int nextIndex = buff.get(i + primesSize);

                while (nextIndex < maxIndex) {
                    marked[nextIndex - baseIndex] = true;
                    nextIndex += prime;
                }

                buff.put(i + primesSize, nextIndex);
            }


            for (int i = 0; i < sieveSize; i++) {
                if (!marked[i]) {

                    int prime = baseIndex + i;
                    buff.put(primesCount, prime);

                    if (primesCount == maxPrimeIndex) {
                        return buff;
                    }

                    // Mark Loop: Begin
                    int nextIndex = prime << 1;

                    while (nextIndex < maxIndex) {

                        marked[nextIndex - baseIndex] = true;
                        nextIndex += prime;
                    }

                    buff.put(primesCount + primesSize, nextIndex);
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
        IntBuffer buff = findPrimes(primesSize, 100);
        System.out.printf("Primes:%n");
        for (int i = 0; i < primesSize; i++) {
            System.out.printf("prime[%d]=%d%n", i + 1, buff.get(i));
        }
    }
}