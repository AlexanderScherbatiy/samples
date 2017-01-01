package datatype.bst

/**
  * Created by alexsch.
  */

sealed abstract class BinarySearchTree[T <% Ordered[T]] {

  def insert(value: T): BinarySearchTree[T] = this match {
    case EmptyBinarySearchTree() => new NonEmptyBinarySeacrhTree[T](value, new EmptyBinarySearchTree(), new EmptyBinarySearchTree())
    case NonEmptyBinarySeacrhTree(v, left, right) =>
      if (value < v) new NonEmptyBinarySeacrhTree(v, left.insert(value), right)
      else if (value > v) new NonEmptyBinarySeacrhTree(v, left, right.insert(value))
      else this
  }


  override def toString: String = this match {
    case EmptyBinarySearchTree() => ""
    case NonEmptyBinarySeacrhTree(v, left, right) => s"($v $left $right)"
  }
}


object BinarySearchTree extends BinarySearchTree[Nothing] {

  def apply[T <% Ordered[T]](values: T*): BinarySearchTree[T] = {
    values.foldLeft[BinarySearchTree[T]](new EmptyBinarySearchTree[T]())((t, value) => t.insert(value))
  }
}

case class EmptyBinarySearchTree[T <% Ordered[T]]() extends BinarySearchTree[T] {
}

case class NonEmptyBinarySeacrhTree[T <% Ordered[T]](value: T, left: BinarySearchTree[T], right: BinarySearchTree[T])
  extends BinarySearchTree[T] {
}
