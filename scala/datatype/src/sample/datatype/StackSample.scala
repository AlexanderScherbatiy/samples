package sample.datatype

import datatype.bst.datatype.stack.EmptyStack
import datatype.bst.datatype.stack.Stack

/**
  * Created by alexsch.
  */
object StackSample extends App {

  val stack: Stack[String] = EmptyStack.push("World!").push("Hello")
  println(s"stack: $stack")
}
