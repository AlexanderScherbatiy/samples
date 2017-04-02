package mathematics.prime

/**
 * Created by alexsch on 4/2/2017.
 */

fun primes(n: Int): List<Int> {
    val primes = mutableListOf<Int>();
    primes(primes, (2..n).toList())
    return primes
}

fun primes(primes: MutableList<Int>, numbers: List<Int>) {
    if (!numbers.isEmpty()) {
        val prime = numbers.first();
        primes(primes, numbers.filter { (it % prime) != 0 })
        primes.add(0, prime)
    }
}
