package sort

import org.scalatest.FunSuite
import sort.Sort._

import scala.util.Random

class SortTest extends FunSuite {

  test("Insert Sort empty list") {
    assert(insertSort(Nil) === Nil)
  }

  test("Insert Sort 1 element") {
    assert(insertSort(1 :: Nil) === 1 :: Nil)
  }

  test("Selection Sort 1 element") {
    assert(selectionSort(1 :: Nil) === 1 :: Nil)
  }


  test("Insert Sort 2 elements") {
    assert(insertSort(1 :: 2 :: Nil) === 1 :: 2 :: Nil)
    assert(insertSort(2 :: 1 :: Nil) === 1 :: 2 :: Nil)
  }

  test("Selection Sort 2 elements") {
    assert(selectionSort(List(1, 2)) === List(1, 2))
    assert(selectionSort(List(2, 1)) === List(1, 2))

  }

  test("Insert Sort 3 elements") {
    assert(insertSort(3 :: 2 :: 1 :: Nil) === 1 :: 2 :: 3 :: Nil)
    assert(insertSort(1 :: 3 :: 2 :: Nil) === 1 :: 2 :: 3 :: Nil)
  }

  test("Selection Sort 3 elements") {
    assert(selectionSort(List(1, 2, 3)) === List(1, 2, 3))
    assert(selectionSort(List(3, 2, 1)) === List(1, 2, 3))
    assert(selectionSort(List(2, 1, 3)) === List(1, 2, 3))
    assert(selectionSort(List(2, 3, 1)) === List(1, 2, 3))

  }

  test("Insert Sort N elements") {

    val N = 100
    val MAX = 1000

    val random = Random

    def randomList(n: Int): List[Int] = {
      if (n == 0) Nil else random.nextInt(MAX) :: randomList(n - 1)
    }

    val list = randomList(N)
    val sortedList = insertSort(list)

    assert(sortedList.zip(sortedList.drop(1)).forall { case (a, b) => a <= b })
  }

  test("Insert Sort strings") {
    assert(insertSort(List("1", "2", "10", "20")) === List("1", "10", "2", "20"))
    val N = 9;
    assert(insertSort((N to 1 by -1).toList.map(_.toString)) === (1 to N).toList.map(_.toString))
  }
}
