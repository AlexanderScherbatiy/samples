package turingmachine.functional.program

import turingmachine.functional._

object TMProgramIncrement extends TMProgram {

  object Q0 extends TMState("Q0")

  object Q1 extends TMState("Q1")

  object QE extends TMState("QE")

  override def run(state: TMState, symbol: TMSymbol): (TMState, TMSymbol, TMStripShift) = (state, symbol) match {

    case (StateBegin, any) => (Q0, any, TMStripShiftRight)

    case (Q0, A0) => (Q0, A1, TMStripShiftRight)
    case (Q0, A1) => (Q1, A0, TMStripShiftRight)
    case (Q0, SymbolEnd) => (StateEnd, SymbolEnd, TMStripShiftMiddle)

    case (Q1, A0) => (Q0, A1, TMStripShiftRight)
    case (Q1, A1) => (Q1, A0, TMStripShiftRight)
    case (Q1, SymbolEnd) => (QE, A1, TMStripShiftRight)

    case (QE, _) => (StateEnd, SymbolEnd, TMStripShiftMiddle)

    case _ => throw new RuntimeException(s"Unknown input: ($state, $symbol)")

  }
}
