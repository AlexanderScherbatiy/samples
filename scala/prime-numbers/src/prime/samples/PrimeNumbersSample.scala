package prime.samples

import prime.datatype.InfinityList

/**
  * Created by alexsch.
  */
object PrimeNumbersSample extends App {

  val N = 100

  def from(n: Int): InfinityList[Int] = InfinityList.cons(n, from(n + 1))

  val naturals = from(1)
  println(s"naturals: ${naturals.toString(N)}")

  def sieve(list: InfinityList[Int]): InfinityList[Int] = InfinityList.cons(list.head, sieve(list.tail.filter(_ % list.head != 0)))
  
  val primes = sieve(from(2))
  println(s"primes: ${primes.toString(N)}")
}
