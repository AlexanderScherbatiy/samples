package sample

object DecompileObject {

  val valField = 10
  var varField = 10

  val valFuncByValueField = (x: Int) => x * x
  var varFuncByValueField = (x: Int) => x * x


  def methodByValue(arg: String): String = {
    s"Hello $arg";
  }

  def methodByName(name: => String): String = {
    s"Hello $name";
  }


  def main(args: Array[String]): Unit = {
    println("Hello, world!")
  }

}
