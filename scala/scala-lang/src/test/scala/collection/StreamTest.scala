package collection

import org.scalatest.FunSuite

class StreamTest extends FunSuite {

  test("Test Stream Concatenation") {

    val stream1 = 1 #:: 2 #:: Stream.empty
    val stream2 = 3 #:: 4 #:: Stream.empty

    println(stream1)

    def concat[T](s1: Stream[T], s2: Stream[T]): Stream[T] = {

      s1 match {
        case Stream.Empty => s2
        case head #:: tail => head #:: concat(tail, s2)
      }
    }

    val stream = concat(stream1, stream2)

    assert(stream.head === 1)
    assert(stream.tail.head === 2)
    assert(stream.tail.tail.head === 3)
    assert(stream.tail.tail.tail.head === 4)
  }
}
