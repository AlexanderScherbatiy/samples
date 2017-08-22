package sort

object Sort {

  def selectionSort[A <% Ordered[A]](list: List[A]) = {

    def extractMax(max: A, l: List[A]): (A, List[A]) = {
      l match {
        case Nil => (max, Nil)
        case head :: tail => if (max < head) {
          val (m, rest) = extractMax(head, tail)
          (m, max :: rest)
        } else {
          val (m, rest) = extractMax(max, tail)
          (m, head :: rest)
        }
      }
    }


    def sort(l: List[A], acc: List[A]): List[A] = {
      l match {
        case Nil => acc
        case head :: tail =>
          val (max, rest) = extractMax(head, tail)
          sort(rest, max :: acc)
      }
    }

    if (list.isEmpty) Nil else sort(list, Nil)
  }

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
