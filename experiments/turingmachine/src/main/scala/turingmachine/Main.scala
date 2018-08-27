import turingmachine.functional._
import turingmachine.functional.program.TMProgramIncrement

object Main {
  def main(args: Array[String]): Unit = {

    val commands = List(SymbolBegin, A0, A1, A0, A1, A1, SymbolEnd)
    println(s"input : $commands")
    val result = FunctionalTM.run(TMProgramIncrement, commands)
    println(s"result: $result")

  }
}