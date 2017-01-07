package sample.datatype

import datatype.bst.BinarySearchTree

/**
  * Created by alexsch.
  */
object BinarySearchTreeSample extends App {

  val tree = BinarySearchTree(-1, 3, 1, -2, 2, 0, -3)
  println(s"tree: $tree")

  for (value <- tree) {
    println(s"tree traverse: $value")
  }

  val twiceTree = tree.map(x => 2 * x)
  println(s"twice tree: $twiceTree")

  val evenTree = tree.filter(x => x % 2 == 0)
  println(s"even tree: $evenTree")

  val squareTree = for (value <- tree if value >= 0) yield value * value
  println(s"square tree: $squareTree")


}
