package turingmachine.functional

import org.scalatest.FunSuite

class FunctionalTuringMachineTest extends FunSuite {

  test("check to/from strips") {

    val commands = List(SymbolBegin, A0, SymbolEnd)
    val strip = FunctionalTuringMachine.toStrip(commands)

    val expectedStrip = StripNode(SymbolBegin, None, Some(StripBranch(A0, Some(StripBranch(SymbolEnd, None)))))
    assert(expectedStrip == strip)
    assert(commands == StripNode.toList(strip))
  }

  test("move strip middle") {

    object SA extends TMSymbol("SA")
    object SB extends TMSymbol("SB")

    val strip = StripNode(SA, Some(StripBranch(SymbolBegin, None)), Some(StripBranch(SymbolEnd, None)))
    val expectedStrip = StripNode(SB, Some(StripBranch(SymbolBegin, None)), Some(StripBranch(SymbolEnd, None)))

    val resultStrip = FunctionalTuringMachine.moveStrip(SB, TMStripShiftMiddle, strip)
    assert(resultStrip == expectedStrip)
  }

  test("move strip right") {

    object SA extends TMSymbol("SA")
    object SB extends TMSymbol("SB")

    val strip = StripNode(SA, Some(StripBranch(SymbolBegin, None)), Some(StripBranch(SymbolEnd, None)))
    val expectedStrip = StripNode(SymbolEnd, Some(StripBranch(SB, Some(StripBranch(SymbolBegin, None)))), None)

    val resultStrip = FunctionalTuringMachine.moveStrip(SB, TMStripShiftRight, strip)
    assert(resultStrip == expectedStrip)
  }

  test("move strip left") {

    object SA extends TMSymbol("SA")
    object SB extends TMSymbol("SB")

    val strip = StripNode(SA, Some(StripBranch(SymbolBegin, None)), Some(StripBranch(SymbolEnd, None)))
    val expectedStrip = StripNode(SymbolBegin, None, Some(StripBranch(SB, Some(StripBranch(SymbolEnd, None)))))

    val resultStrip = FunctionalTuringMachine.moveStrip(SB, TMStripShiftLeft, strip)
    assert(resultStrip == expectedStrip)
  }
}
