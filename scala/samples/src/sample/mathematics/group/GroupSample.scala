package sample.mathematics.group

/**
  * Created by alexsch on 3/18/2017.
  */

import mathematics.group._

object GroupSample {

  def main(args: Array[String]): Unit = {

    val group = Group2
    println(s"Group order: ${group.order}")

    println("Group multiplication table: ")
    val elements = group.elements
    for (g1 <- elements) {
      for (g2 <- elements) {
        println(s"$g1 * $g2 = ${g1 * g2}")
      }
    }
  }
}
