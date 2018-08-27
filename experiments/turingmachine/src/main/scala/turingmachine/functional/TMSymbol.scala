package turingmachine.functional

abstract class TMSymbol(name: String) {
  override def toString: String = name
}

object SymbolBegin extends TMSymbol("BEGIN")

object SymbolEnd extends TMSymbol("END")

object SymbolUndefined extends TMSymbol("UNDEFINED")

object A0 extends TMSymbol("0")

object A1 extends TMSymbol("1")
