package sort

object Sort {

  def insertSort(list: List[Int]) = {

    def insert(value: Int, list: List[Int]): List[Int] = list match {
      case Nil => value :: Nil
      case head :: tail => if (value > head) head :: insert(value, tail) else value :: list
    }

    def sort(l: List[Int]): List[Int] = l match {
      case Nil => Nil
      case head :: tail => insert(head, sort(tail))
    }

    sort(list)
  }
}
