package mathematics.group

/**
  * Created by alexsch on 3/18/2017.
  */
abstract class AbstractGroup {

  type GroupElement <: AbstractGroupElem

  def name: String

  def order: Int

  def elements: Iterable[GroupElement]

  abstract class AbstractGroupElem {

    def inverse(): GroupElement

    def *(that: GroupElement): GroupElement
  }

}