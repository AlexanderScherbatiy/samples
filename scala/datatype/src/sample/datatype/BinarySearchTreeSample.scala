package sample.datatype

import datatype.bst.BinarySearchTree

/**
  * Created by alexsch.
  */
object BinarySearchTreeSample extends App {

  val tree = BinarySearchTree(2, 1, 4, 3, 5)
  println(s"tree: $tree")

  val squareTree = tree.map(x => x * x)
  println(s"square tree: $squareTree")
}
