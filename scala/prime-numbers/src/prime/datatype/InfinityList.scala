package prime.datatype

/**
  * Created by alexsch.
  */
trait InfinityList[A] {

  def isEmpty: Boolean

  def head: A

  def tail: InfinityList[A]

  def filter(p: A => Boolean): InfinityList[A] = {
    if (isEmpty) this
    else if (p(head)) InfinityList.cons(head, tail.filter(p))
    else tail.filter(p)
  }

  def toString(n: Int): String = if (n == 0) "" else s"$head ${tail.toString(n - 1)}"
}

object InfinityList {
  def cons[A](h: A, t: => InfinityList[A]) = new InfinityList[A] {
    override def isEmpty = false

    override def head = h

    lazy val tail: InfinityList[A] = t
  }

  val empty = new InfinityList[Nothing] {
    override def isEmpty = true

    override def head = sys.error("empty.head")

    override def tail = sys.error("empty.head")
  }
}
