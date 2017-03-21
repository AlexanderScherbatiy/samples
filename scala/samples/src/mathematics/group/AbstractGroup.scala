package mathematics.group

/**
  * Created by alexsch on 3/18/2017.
  */
abstract class AbstractGroup {

  type GroupElement <: AbstractGroupElem

  def name: String

  def order: Int

  def identity: GroupElement

  def elements: Iterable[GroupElement]

  abstract class AbstractGroupElem {

    def isIdentity(): Boolean

    def inverse(): GroupElement

    def *(that: GroupElement): GroupElement

    def cyclicGroup(): Iterable[GroupElement] = {

      def cyclicGroup(elem: GroupElement, list: List[GroupElement]): List[GroupElement] = {
        if (elem.isIdentity()) {
          list
        } else {
          elem :: cyclicGroup(this * elem, list)
        }
      }

      identity :: cyclicGroup(this.toGroupElement, List[GroupElement]())
    }

    def toGroupElement: GroupElement
  }

}