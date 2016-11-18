package prime;

import java.util.Iterator;

/**
 * Created by alexsch on 11/17/2016.
 */
public class SequentialPrimeNumbers implements PrimeNumbers {

    @Override
    public Iterable<Long> findPrimeNumbers(long primeNumbersCount) {

        PrimeNumberItem head = new PrimeNumberItem(2);
        PrimeNumberItem tail = head;
        long currentPrimeNumbersCount = 1;
        long n = 2;

        mainLoop:
        while (currentPrimeNumbersCount < primeNumbersCount) {
            n++;
            PrimeNumberItem current = head;
            while (current != null) {
                if (n % current.prime == 0) {
                    continue mainLoop;
                }
                current = current.next;
            }

            tail.next = new PrimeNumberItem(n);
            tail = tail.next;
            currentPrimeNumbersCount++;
        }

        return () ->
                new Iterator<Long>() {

                    PrimeNumberItem item = head;

                    @Override
                    public boolean hasNext() {
                        return item != null;
                    }

                    @Override
                    public Long next() {
                        long prime = item.prime;
                        item = item.next;
                        return prime;
                    }
                };
    }

    public static class PrimeNumberItem {
        private final long prime;
        private PrimeNumberItem next;

        private PrimeNumberItem(long n) {
            this.prime = n;
        }
    }
}