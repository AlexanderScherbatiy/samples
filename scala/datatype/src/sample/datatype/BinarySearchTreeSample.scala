package sample.datatype

import datatype.bst.BinarySearchTree

/**
  * Created by alexsch.
  */
object BinarySearchTreeSample extends App {

  val tree = BinarySearchTree(2, 1, 4, 3, 5)
  println(s"tree: $tree")

  for (value <- tree) {
    println(s"tree traverse: $value")
  }

  val twiceTree = tree.map(x => 2 * x)
  println(s"twice tree: $twiceTree")

  val squareTree = for (value <- tree) yield value * value
  println(s"square tree: $squareTree")

}
