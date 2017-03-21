package sample.mathematics.group

/**
  * Created by alexsch on 3/18/2017.
  */

import mathematics.group._

object GroupSample {

  def main(args: Array[String]): Unit = {

    printInfo(Group2)
    printInfo(new PermutationGroup(2))
    printInfo(new PermutationGroup(3))
  }


  def printInfo(group: AbstractGroup): Unit = {

    println(s"Group: ${group.name}")
    println(s"order: ${group.order}")
    val e = group.identity

    println(s"identity: $e, is identity: ${e.isIdentity()}")

    val elements = group.elements

    println()
    println("elements:")
    for (elem <- elements) {

      println(s"elem: $elem, inverse: ${elem.inverse()}, cyclic group: ${elem.cyclicGroup()}")
    }
    println()

    println("Group multiplication table: ")
    for (g1 <- elements) {
      for (g2 <- elements) {
        println(s"$g1 * $g2 = ${g1 * g2}")
      }
    }
    println()
  }
}