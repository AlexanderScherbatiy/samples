package lang.traits

import org.scalatest.FunSuite
import scala.collection.mutable.ArrayBuffer

class StackableModificationTest extends FunSuite {

  abstract class IntQueue {
    def get(): Int
    def put(x: Int)
  }

  class BasicIntQueue extends IntQueue {
    private val buf = new ArrayBuffer[Int]()

    def get() = buf.remove(0)
    def put(x: Int) = buf += x
  }

  trait DoublingQueue extends IntQueue {
    abstract override def put(x: Int): Unit = super.put(2 * x)
  }


  trait IncrementingQueue extends IntQueue {
    abstract override def put(x: Int): Unit = super.put(x + 1)
  }

  test("Basic Stackable Modification") {

    val queue = new BasicIntQueue
    queue.put(10)
    queue.put(20)

    assert(queue.get() === 10)
    assert(queue.get() === 20)
  }

  test("Doubling Stackable Modification") {

    val queue = new BasicIntQueue with DoublingQueue
    queue.put(10)
    queue.put(20)

    assert(queue.get() === 20)
    assert(queue.get() === 40)
  }

  test("Incrementing Stackable Modification") {

    val queue = new BasicIntQueue with IncrementingQueue
    queue.put(10)
    queue.put(20)

    assert(queue.get() === 11)
    assert(queue.get() === 21)
  }

  test("Doubling Incrementing Stackable Modification") {

    val queue = new BasicIntQueue with IncrementingQueue with DoublingQueue
    queue.put(10)
    queue.put(20)

    assert(queue.get() === 21)
    assert(queue.get() === 41)
  }

  test("Incrementing Doubling Stackable Modification") {

    val queue = new BasicIntQueue with DoublingQueue with IncrementingQueue
    queue.put(10)
    queue.put(20)

    assert(queue.get() === 22)
    assert(queue.get() === 42)
  }
}
