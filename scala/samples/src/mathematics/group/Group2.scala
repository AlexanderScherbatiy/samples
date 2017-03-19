package mathematics.group

/**
  * Created by alexsch on 3/19/2017.
  */
object Group2 extends AbstractGroup {

  override type GroupElement = Group2Element

  override def order = 2

  override def elements = List(E, G)

  object E extends Group2Element {
    override def *(that: GroupElement) = that

    override def toString: String = "e"
  }

  object G extends Group2Element {
    override def *(that: GroupElement) = that match {
      case E => G
      case G => E
    }

    override def toString: String = "g"
  }

  abstract class Group2Element extends AbstractGroupElem {
  }

}
