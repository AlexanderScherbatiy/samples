package sample.algorithm;

import java.util.Arrays;

/**
 * Created by alexsch on 4/21/2017.
 */
public class Prime {


    public static int[] primes(int n) {

        boolean[] marked = new boolean[n + 1];
        int count = 0;
        int[] primes = new int[n / 2 + 1];

        for (int i = 2; i <= n; i++) {
            if (marked[i]) {
                continue;
            }

            primes[count++] = i;

            int s = i + i;
            while (s <= n) {
                marked[s] = true;
                s += i;
            }
        }

        return Arrays.copyOf(primes, count);
    }

    public static void main(String[] args) {
        int[] primes = primes(100);

        for (int i = 0; i < primes.length; i++) {
            System.out.printf("%d ", primes[i]);
        }

        System.out.printf("%n");
    }
}
