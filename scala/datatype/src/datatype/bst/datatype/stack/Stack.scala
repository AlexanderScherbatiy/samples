package datatype.bst.datatype.stack

/**
  * Created by alexsch.
  */
sealed abstract class Stack[+A] {

  def isEmpty: Boolean;

  def push[B >: A](elem: B): Stack[B] = new NonEmptyStack[B](elem, this)

  def top: A

  def pop: Stack[A]

  override def toString: String = this match {
    case EmptyStack => ""
    case NonEmptyStack(elem, rest) => elem + " " + rest
  }
}

case object EmptyStack extends Stack[Nothing] {
  override def isEmpty: Boolean = true
  override def top = sys.error("empty stack: top")
  override def pop = sys.error("empty stack: pop")
}

case class NonEmptyStack[A](elem: A, rest: Stack[A]) extends Stack[A] {
  override def isEmpty: Boolean = false
  override def top: A = elem
  override def pop: Stack[A] = rest
}
