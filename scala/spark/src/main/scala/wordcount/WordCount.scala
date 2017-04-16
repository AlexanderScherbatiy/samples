package wordcount

import java.io.File

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by alexsch on 4/16/2017.
  */
object WordCount {

  val conf: SparkConf = new SparkConf()
    .setAppName("word-count[*]")
    .setMaster("local")

  val sc: SparkContext = new SparkContext(conf)

  def filePath = {
    new File(this.getClass.getClassLoader.getResource("wikipedia/wikipedia.dat").toURI).getPath
  }

  def main(args: Array[String]): Unit = {

    val wikipediaFile = sc.textFile(filePath)

    val counts = wikipediaFile.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)

    val top100 = counts.top(100)(Ordering.by { e: (String, Int) => e._2 })

    println(s"wikipedia top 100:\n${top100.mkString("\n")}")
  }
}
