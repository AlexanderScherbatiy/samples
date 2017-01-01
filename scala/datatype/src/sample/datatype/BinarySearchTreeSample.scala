package sample.datatype

import datatype.bst.BinarySearchTree

/**
  * Created by alexsch.
  */
object BinarySearchTreeSample {

  def main(args: Array[String]): Unit = {

    val tree = BinarySearchTree(2, 1, 4, 3, 5)
    println(s"tree: $tree")

  }
}
