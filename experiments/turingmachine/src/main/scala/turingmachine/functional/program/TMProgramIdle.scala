package turingmachine.functional.program

import turingmachine.functional._

class TMProgramIdle extends TMProgram {

  override def run(state: TMState, symbol: TMSymbol): (TMState, TMSymbol, TMStripShift) = (state, symbol) match {

    case (StateBegin, SymbolBegin) => (StateEnd, SymbolBegin, TMStripShiftMiddle)
    case _ => throw new RuntimeException(s"Unknown input: ($state, $symbol)")
  }
}
