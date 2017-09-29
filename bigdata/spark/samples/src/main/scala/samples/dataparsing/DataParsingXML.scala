package samples.dataparsing

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.functions._

object DataParsingXML {

  def main(args: Array[String]): Unit = {

    val sc = new SparkContext(new SparkConf().setAppName("books-xml-parsing").setMaster("local[*]"))
    val booksXML = "data/parsing/xml/books.xml"
    val outDir = "output/data"
    parseBooksXML(sc, booksXML, outDir)
  }

  def parseBooksXML(sc: SparkContext, books: String, result: String): Unit = {
    val sqlContext = new SQLContext(sc)

    val df = sqlContext.read
      .format("com.databricks.spark.xml")
      .option("rowTag", "book")
      .load(books)

    val computerBooks = df.where(col("genre") === "Computer").select("@id", "author", "title")

    computerBooks.write
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .save(result)
  }
}
