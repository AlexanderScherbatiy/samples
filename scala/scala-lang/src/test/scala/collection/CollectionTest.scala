package collection

import org.scalatest.FunSuite

class CollectionTest extends FunSuite {

  test("Test fold/reduce") {

    val list = List(1, 2, 3, 4, 5, 6)

    val reduceSum = list.reduce((a, b) => a + b)
    assert(reduceSum == 21)

    val foldSum = list.foldLeft(0)((a, b) => a + b)
    assert(foldSum == 21)

    val foldWordsSize = List("Hello", "World", "Scala").foldLeft(0)((sum, word) => sum + word.length)

    assert(foldWordsSize === 15)
  }

  test("Word Count") {

    val words = "Hello World Hello Scala".split(' ')

    val wordCount = words
      .map(word => (word, 1))
      .groupBy { case (word, _) => word }
      .map { case (word, list) => (word, list.map { case (_, count) => count }.sum) }

    assert(wordCount("Hello") === 2)
    assert(wordCount("World") === 1)
    assert(wordCount("Scala") === 1)
  }
}
