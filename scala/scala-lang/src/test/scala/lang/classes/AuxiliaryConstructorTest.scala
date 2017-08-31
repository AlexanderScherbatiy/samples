package lang.classes

import org.scalatest.FunSuite

class AuxiliaryConstructorTest extends FunSuite {

  class Rational(enumerator: Int, denominator: Int) {

    def this(enumerator: Int) = this(enumerator, 1)

    override def toString: String = if (denominator == 1) s"$enumerator" else s"$enumerator/$denominator"
  }

  test("Constructor Test") {
    val half = new Rational(1, 2)
    assert(half.toString == "1/2")

    val two = new Rational(2)
    assert(two.toString == "2")
  }
}
