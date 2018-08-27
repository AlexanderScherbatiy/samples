package turingmachine.functional.program

import org.scalatest.FunSuite
import turingmachine.functional._

class TMProgramIncrementTest extends FunSuite {

  test("check increment program") {

    assert(run(List(SymbolBegin, SymbolEnd)) == List(SymbolBegin, SymbolEnd))

    assert(run(List(SymbolBegin, A0, SymbolEnd)) == List(SymbolBegin, A1, SymbolEnd))
    assert(run(List(SymbolBegin, A1, SymbolEnd)) == List(SymbolBegin, A0, A1, SymbolEnd))

    assert(run(List(SymbolBegin, A0, A0, SymbolEnd)) == List(SymbolBegin, A1, A1, SymbolEnd))
    assert(run(List(SymbolBegin, A1, A0, SymbolEnd)) == List(SymbolBegin, A0, A1, SymbolEnd))
    assert(run(List(SymbolBegin, A0, A1, SymbolEnd)) == List(SymbolBegin, A1, A0, A1, SymbolEnd))
    assert(run(List(SymbolBegin, A1, A1, SymbolEnd)) == List(SymbolBegin, A0, A0, A1, SymbolEnd))
  }

  def run(commands: List[TMSymbol]): List[TMSymbol] = FunctionalTM.run(TMProgramIncrement, commands)
}
