package datatype.bst

/**
  * Created by alexsch.
  */

sealed abstract class BinarySearchTree[A <% Ordered[A]] {

  def insert(value: A): BinarySearchTree[A] = this match {
    case EmptyBinarySearchTree() => new NonEmptyBinarySeacrhTree[A](value, new EmptyBinarySearchTree(), new EmptyBinarySearchTree())
    case NonEmptyBinarySeacrhTree(v, left, right) =>
      if (value < v) new NonEmptyBinarySeacrhTree(v, left.insert(value), right)
      else if (value > v) new NonEmptyBinarySeacrhTree(v, left, right.insert(value))
      else this
  }

  override def toString: String = this match {
    case EmptyBinarySearchTree() => ""
    case NonEmptyBinarySeacrhTree(v, left, right) => s"($v $left $right)"
  }


  def map[B <% Ordered[B]](f: A => B): BinarySearchTree[B] = this match {
    case EmptyBinarySearchTree() => new EmptyBinarySearchTree[B]
    case NonEmptyBinarySeacrhTree(v, left, right) => new NonEmptyBinarySeacrhTree[B](f(v), left.map(f), right.map(f))
  }
}

object BinarySearchTree {

  def apply[A <% Ordered[A]](values: A*): BinarySearchTree[A] = {
    values.foldLeft[BinarySearchTree[A]](new EmptyBinarySearchTree[A]())((t, value) => t.insert(value))
  }
}

case class EmptyBinarySearchTree[A <% Ordered[A]]() extends BinarySearchTree[A] {
}

case class NonEmptyBinarySeacrhTree[A <% Ordered[A]](value: A, left: BinarySearchTree[A], right: BinarySearchTree[A])
  extends BinarySearchTree[A] {
}
