package collection

abstract class TraversableMap[+T] extends Traversable[T] {

  override def foreach[U](f: (T) => U): Unit = {
    this match {
      case TraversableNode(value, left, right) =>
        f(value)
        left.foreach(f)
        right.foreach(f)
      case TraversableLeaf =>
    }
  }
}

case class TraversableNode[+T](value: T, left: TraversableMap[T], right: TraversableMap[T]) extends TraversableMap[T]

object TraversableLeaf extends TraversableMap[Nothing]
