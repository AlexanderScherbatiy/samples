package turingmachine.functional

private[functional] case class TMStrip(value: TMSymbol, prev: Option[StripBranch], next: Option[StripBranch]) {

  override def toString: String =
    TMStrip.toLeftList(prev).mkString("(", " ", "[") + value + TMStrip.toRightList(next).mkString("]", " ", ")")
}

case class StripBranch(value: TMSymbol, next: Option[StripBranch])

private[functional] object TMStrip {

  def moveStrip(symbol: TMSymbol, shift: TMStripShift, strip: TMStrip): TMStrip = shift match {

    case TMStripShiftMiddle => TMStrip(symbol, strip.prev, strip.next)
    case TMStripShiftRight => strip.next match {
      case Some(StripBranch(s, next)) => TMStrip(s, Some(StripBranch(symbol, strip.prev)), next)
      case None => TMStrip(SymbolUndefined, Some(StripBranch(symbol, strip.prev)), None)
    }
    case TMStripShiftLeft => strip.prev match {
      case Some(StripBranch(s, prev)) => TMStrip(s, prev, Some(StripBranch(symbol, strip.next)))
      case None => TMStrip(SymbolUndefined, None, Some(StripBranch(symbol, strip.next)))
    }
  }

  def toRightList(branch: Option[StripBranch]): List[TMSymbol] = branch match {
    case Some(StripBranch(v, b)) => v :: toRightList(b)
    case None => Nil
  }

  def toLeftList(branch: Option[StripBranch]): List[TMSymbol] = branch match {
    case Some(StripBranch(v, b)) => toLeftList(b) :+ v
    case None => Nil
  }

  def toList(strip: TMStrip): List[TMSymbol] = {
    TMStrip.toLeftList(strip.prev) ::: (strip.value :: TMStrip.toRightList(strip.next))
  }

  def toStrip(commands: List[TMSymbol]): TMStrip = {
    def toStripBranch(commands: List[TMSymbol]): Option[StripBranch] = commands match {
      case head :: tail => Some(StripBranch(head, toStripBranch(tail)))
      case _ => None
    }

    commands match {
      case head :: tail => TMStrip(head, None, toStripBranch(tail))
      case _ => throw new RuntimeException("Strip should start at least from one symbol!")
    }
  }
}
