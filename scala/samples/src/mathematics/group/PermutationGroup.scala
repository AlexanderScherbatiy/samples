package mathematics.group

import scala.collection.mutable

/**
  * Created by alexsch on 3/19/2017.
  */
class PermutationGroup(n: Int) extends AbstractGroup {
  override type GroupElement = Permutation

  override val name = s"Permutation($n)"
  override val elements: Iterable[GroupElement] = {

    val values = Array.ofDim[Int](n);
    val used = Array.ofDim[Boolean](n);
    val list = mutable.ListBuffer[Array[Int]]()
    permutations(values, used, 0, list)
    list.map(elem => new Permutation(elem))
  }

  override val order: Int = elements.size

  override val identity = new Permutation((0 to n - 1).toArray)

  def permutations(elems: Array[Int], used: Array[Boolean], k: Int, list: mutable.ListBuffer[Array[Int]]) {

    if (k == n) {
      list += elems.clone()

    } else {
      for (i <- elems.indices) {
        if (!used(i)) {
          used(i) = true
          elems(k) = i
          permutations(elems, used, k + 1, list)
          used(i) = false
        }
      }
    }
  }

  class Permutation(seq: IndexedSeq[Int]) extends AbstractGroupElem {

    val elems = seq

    override def isIdentity() = elems.indices.forall(i => elems(i) == i)

    override def inverse() = {
      val inverse = Array.ofDim[Int](n)
      for (i <- elems.indices) {
        inverse(elems(i)) = i
      }
      new Permutation(inverse)
    }

    override def *(that: Permutation): Permutation = {

      val result = Array.ofDim[Int](n)
      for (i <- elems.indices) {
        result(i) = that.elems(elems(i))
      }
      new Permutation(result)
    }

    override def toGroupElement = this

    override def toString: String = elems.map(e => e + 1).mkString("[", " ", "]")
  }

}


