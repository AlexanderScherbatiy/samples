package turingmachine.functional

object FunctionalTM {

  def run(program: TMProgram, commands: List[TMSymbol]): List[TMSymbol] =
    TMStrip.toList(run(program, StateBegin, TMStrip.toStrip(commands)))


  private[functional] def run(program: TMProgram, state: TMState, strip: TMStrip): TMStrip = {

    val (s, symbol, shift) = program.run(state, strip.value)
    val nextStrip = TMStrip.moveStrip(symbol, shift, strip)

    if (StateEnd == s) {
      nextStrip
    } else {
      run(program, s, nextStrip)
    }
  }
}


