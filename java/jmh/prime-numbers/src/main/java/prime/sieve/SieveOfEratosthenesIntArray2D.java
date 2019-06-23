package prime.sieve;

public class SieveOfEratosthenesIntArray2D {

    // Prime numbers and next indices are stored in separate arrays.
    public static int[][] findPrimes(int rows, int columns, int sieveSize) {

        int maxPrimeRow = rows - 1;
        int maxPrimeColumn = columns - 1;

        int[][] primes = new int[rows][columns];
        int[][] nextIndices = new int[rows][columns];
        primes[0][0] = 2;
        nextIndices[0][0] = 4;


        int primeRow = 0;
        int primeColumn = 1;


        int baseIndex = 0;
        int maxIndex = sieveSize;
        boolean marked[] = new boolean[sieveSize];
        marked[0] = true;
        marked[1] = true;
        if (sieveSize > 2) {
            marked[2] = true;
        }


        while (true) {

            loop:
            for (int i = 0; i <= rows; i++) {
                for (int j = 0; j < columns; j++) {

                    if (i == primeRow && j == primeColumn) {
                        break loop;
                    }

                    // Mark Loop: Begin
                    int prime = primes[i][j];
                    int nextIndex = nextIndices[i][j];

                    while (nextIndex < maxIndex) {
                        marked[nextIndex - baseIndex] = true;
                        nextIndex += prime;
                    }

                    nextIndices[i][j] = nextIndex;
                    // Mark Loop: End
                }
            }


            for (int i = 0; i < sieveSize; i++) {
                if (!marked[i]) {

                    int prime = baseIndex + i;
                    primes[primeRow][primeColumn] = prime;

                    if (primeRow == maxPrimeRow && primeColumn == maxPrimeColumn) {
                        return primes;
                    }

                    // Mark Loop: Begin
                    int nextIndex = prime << 1;

                    while (nextIndex < maxIndex) {
                        marked[nextIndex - baseIndex] = true;
                        nextIndex += prime;
                    }

                    nextIndices[primeRow][primeColumn] = nextIndex;
                    // Mark Loop: End

                    primeColumn++;
                    if (primeColumn == columns) {
                        primeColumn = 0;
                        primeRow++;
                    }


                } else {
                    marked[i] = false;
                }
            }

            baseIndex = maxIndex;
            maxIndex += sieveSize;
        }
    }

    public static void main(String[] args) {
        int primeRows = 100;
        int primeColumns = 100;
        int[][] primes = findPrimes(primeRows, primeColumns, 100);
        System.out.printf("Primes:%n");
        int primesNum = 1;
        for (int i = 0; i < primeRows; i++) {
            for (int j = 0; j < primeColumns; j++) {
                System.out.printf("prime[%d]=%d%n", primesNum++, primes[i][j]);
            }
        }
    }
}