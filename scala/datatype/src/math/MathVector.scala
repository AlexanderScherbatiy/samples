package math

/**
  * Created by alexsch on 2/10/2017.
  */
class MathVector(val elems: IndexedSeq[Double]) {

  def add(vector: MathVector): MathVector = new MathVector((elems zip vector.elems) map (e => e._1 + e._2))

  override def toString(): String = elems toString()
}

object MathVector {

  def apply(elems: Double*): MathVector = new MathVector(elems.toIndexedSeq)
}
