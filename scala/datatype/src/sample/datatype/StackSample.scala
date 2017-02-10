package sample.datatype

import datatype.stack.EmptyStack
import datatype.stack.{EmptyStack, Stack}

/**
  * Created by alexsch.
  */
object StackSample extends App {

  val stack: Stack[String] = EmptyStack.push("World!").push("Hello")
  println(s"stack: $stack")
}
