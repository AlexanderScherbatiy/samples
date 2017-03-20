package sample.mathematics.group

import mathematics.group.PermutationGroup

/**
  * Created by alexsch on 3/19/2017.
  */
object PermutationGroupSample {

  def main(args: Array[String]): Unit = {

    val N = 3
    val permutations = new PermutationGroup(N)
    val elements = permutations.elements

    println(s"Permutations group order: ${permutations.order}")
    println()
    println("elements:")
    elements.foreach(println)

    println()
    println("multiplication table:")

    for (g1 <- elements) {
      for (g2 <- elements) {
        println(s"$g1 * $g2 = ${g1 * g2}")
      }
    }
  }
}