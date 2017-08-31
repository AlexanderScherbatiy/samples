package lib.datatype

import org.scalatest.FunSuite

import java.io.Closeable

class AutoCloseableTest extends FunSuite {

  class TestAutoCloseable extends Closeable {

    var isClosed = false;
    var buf = ""

    def write(value: String) = if (!isClosed) buf += value

    override def close(): Unit = isClosed = true
  }

  def autoClose[A <: Closeable](closeable: A)(f: A => Unit): Unit = {

    try {
      f(closeable)
    } finally {
      if (closeable != null) {
        closeable.close()
      }
    }
  }

  test("AutoClosable Test") {

    val autoCloseable = new TestAutoCloseable()

    assert(!autoCloseable.isClosed)

    autoClose(autoCloseable)(
      closeable => {
        closeable.write("123")
        closeable.write("456")
      }
    )

    autoCloseable.write("789")

    assert(autoCloseable.isClosed)
    assert(autoCloseable.buf === "123456")
  }

  test("Null AutoClosable Test") {
    autoClose(null)(_ => {})
  }
}
