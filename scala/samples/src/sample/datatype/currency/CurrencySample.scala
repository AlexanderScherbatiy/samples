package sample.datatype.currency

import datatype.currency.{Europe, US}

/**
  * Created by alexsch on 3/19/2017.
  */
object CurrencySample {

  def main(args: Array[String]): Unit = {

    println(s"Euros: ${Europe.Euro * 5 + Europe.Cent * 15}")
    println(s"Dollars: ${US.Dollar * 3 + US.Cent * 25}")
    //println(s"Mixed: ${Europe.Euro * 35 + US.Cent * 25}") // impossible
  }
}
