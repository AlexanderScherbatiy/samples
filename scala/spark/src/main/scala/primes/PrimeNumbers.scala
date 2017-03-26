import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.annotation.tailrec

object PrimeNumbers {

  val conf: SparkConf = new SparkConf()
    .setAppName("prime-numbers")
    .setMaster("local")

  val sc: SparkContext = new SparkContext(conf)

  def N = 500

  def rdd = sc.parallelize(2 to N)

  @tailrec
  def primes(numbers: RDD[Int], list: List[Int]): List[Int] = {

    if (numbers.isEmpty()) {
      list
    } else {
      val prime = numbers.first()
      primes(numbers.filter(_ % prime != 0), prime :: list)
    }
  }

  def main(args: Array[String]): Unit = {
    val list = primes(rdd, Nil)
    println(s"primes: $list")
  }
}