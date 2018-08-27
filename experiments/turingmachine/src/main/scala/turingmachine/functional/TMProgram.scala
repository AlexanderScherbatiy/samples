package turingmachine.functional

abstract class TMProgram {
  def run(state: TMState, symbol: TMSymbol): (TMState, TMSymbol, TMStripShift)
}
