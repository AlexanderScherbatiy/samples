package sample.math

import math.MathVector

/**
  * Created by alexsch on 2/10/2017.
  */
object MathVectorSample extends App {

  println("Math Vector Sample")
  val v1 = MathVector(1, 2, 3)
  val v2 = MathVector(2, 3, 4)

  val sum = v1 add v2;
  println(s"$v1 + $v2 = $sum")
}
