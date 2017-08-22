package sort

object Sort {

  def insertSort[A <% Ordered[A]](list: List[A]) = {

    def insert(value: A, list: List[A]): List[A] = list match {
      case Nil => value :: Nil
      case head :: tail => if (value <= head) value :: list else head :: insert(value, tail)
    }

    def sort(l: List[A]): List[A] = l match {
      case Nil => Nil
      case head :: tail => insert(head, sort(tail))
    }

    sort(list)
  }
}
