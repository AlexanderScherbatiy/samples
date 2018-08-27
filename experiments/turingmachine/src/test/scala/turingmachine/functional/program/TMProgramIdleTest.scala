package turingmachine.functional.program

import org.scalatest.FunSuite
import turingmachine.functional._

class TMProgramIdleTest extends FunSuite {

  test("check that idle program does not change the strip") {
    val commands = List(SymbolBegin, A0, A1, A0, A1, A1, SymbolEnd)
    val program = new TMProgramIdle
    val result = FunctionalTM.run(program, commands)
    assert(commands == result)
  }
}
