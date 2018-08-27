package turingmachine.functional

import org.scalatest.FunSuite

class TMStripTest extends FunSuite {

  test("check to/from strips") {

    val commands = List(SymbolBegin, A0, SymbolEnd)
    val strip = TMStrip.toStrip(commands)

    val expectedStrip = TMStrip(SymbolBegin, None, Some(StripBranch(A0, Some(StripBranch(SymbolEnd, None)))))
    assert(expectedStrip == strip)
    assert(commands == TMStrip.toList(strip))
  }

  test("move strip middle") {

    object SA extends TMSymbol("SA")
    object SB extends TMSymbol("SB")

    val strip = TMStrip(SA, Some(StripBranch(SymbolBegin, None)), Some(StripBranch(SymbolEnd, None)))
    val expectedStrip = TMStrip(SB, Some(StripBranch(SymbolBegin, None)), Some(StripBranch(SymbolEnd, None)))

    val resultStrip = TMStrip.moveStrip(SB, TMStripShiftMiddle, strip)
    assert(resultStrip == expectedStrip)
  }

  test("move strip right") {

    object SA extends TMSymbol("SA")
    object SB extends TMSymbol("SB")

    val strip = TMStrip(SA, Some(StripBranch(SymbolBegin, None)), Some(StripBranch(SymbolEnd, None)))
    val expectedStrip = TMStrip(SymbolEnd, Some(StripBranch(SB, Some(StripBranch(SymbolBegin, None)))), None)

    val resultStrip = TMStrip.moveStrip(SB, TMStripShiftRight, strip)
    assert(resultStrip == expectedStrip)
  }

  test("move strip left") {

    object SA extends TMSymbol("SA")
    object SB extends TMSymbol("SB")

    val strip = TMStrip(SA, Some(StripBranch(SymbolBegin, None)), Some(StripBranch(SymbolEnd, None)))
    val expectedStrip = TMStrip(SymbolBegin, None, Some(StripBranch(SB, Some(StripBranch(SymbolEnd, None)))))

    val resultStrip = TMStrip.moveStrip(SB, TMStripShiftLeft, strip)
    assert(resultStrip == expectedStrip)
  }
}
