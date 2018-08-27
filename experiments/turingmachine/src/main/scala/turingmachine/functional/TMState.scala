package turingmachine.functional

abstract class TMState(name: String) {
  override def toString: String = name
}

object StateBegin extends TMState("Begin")

object StateEnd extends TMState("End")
