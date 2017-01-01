package datatype.bst

/**
  * Created by alexsch.
  */

sealed abstract class BinarySearchTree {

  def insert(value: Double): BinarySearchTree = this match {
    case EmptyBinarySearchTree => new NonEmptyBinarySeacrhTree(value, EmptyBinarySearchTree, EmptyBinarySearchTree)
    case NonEmptyBinarySeacrhTree(v, left, right) =>
      if (value < v) new NonEmptyBinarySeacrhTree(v, left.insert(value), right)
      else if (value > v) new NonEmptyBinarySeacrhTree(v, left, right.insert(value))
      else this
  }

  override def toString: String = this match {
    case EmptyBinarySearchTree => ""
    case NonEmptyBinarySeacrhTree(v, left, right) => s"($v $left $right)"
  }
}


object BinarySearchTree extends BinarySearchTree {

  def apply(values: Double*): BinarySearchTree = {
    values.foldLeft[BinarySearchTree](EmptyBinarySearchTree)((t, value) => t.insert(value))
  }
}

case object EmptyBinarySearchTree extends BinarySearchTree {
}

case class NonEmptyBinarySeacrhTree[T](value: Double, left: BinarySearchTree, right: BinarySearchTree) extends BinarySearchTree {
}
