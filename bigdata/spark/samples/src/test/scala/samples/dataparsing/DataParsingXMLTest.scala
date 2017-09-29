package samples.dataparsing

import java.io.{File, PrintWriter}

import org.apache.spark.{SparkConf, SparkContext}
import org.junit.rules.TemporaryFolder
import org.junit.{Rule, Test}
import org.scalatest.junit.JUnitSuite

import scala.io.Source

class DataParsingXMLTest extends JUnitSuite {

  val _temporaryFolder = new TemporaryFolder()

  @Rule
  def temporaryFolder = _temporaryFolder

  @Test
  def parseBooksXML(): Unit = {

    val xmlFile = temporaryFolder.newFile("books.xml");
    val csvFile = new File(temporaryFolder.getRoot(), "books.csv");
    val outDir = new File(temporaryFolder.getRoot(), "out");

    saveToFile(xmlFile, booksXML)

    val sc = new SparkContext(new SparkConf().setAppName("books-xml-parsing").setMaster("local[*]"))
    DataParsingXML.parseBooksXML(sc, xmlFile.getAbsolutePath, outDir.getAbsolutePath);

    val outCSV = readFromDir(outDir)
    assert(outCSV === booksCSV.split("\n"))
  }
  
  def saveToFile(file: File, content: String): Unit = {
    val pw = new PrintWriter(file)
    pw.write(content)
    pw.close()
  }

  def readFromDir(dir: File): Seq[String] = {
    dir.listFiles()
      .filter(_.getName().startsWith("part-"))
      .flatMap(f => Source.fromFile(f).getLines().toList)
  }

  val booksXML =
    """
      |<?xml version="1.0"?>
      |<catalog>
      |    <book id="bk101">
      |        <author>Gambardella, Matthew</author>
      |        <title>XML Developer's Guide</title>
      |        <genre>Computer</genre>
      |        <price>44.95</price>
      |        <publish_date>2000-10-01</publish_date>
      |        <description>An in-depth look at creating applications with XML.</description>
      |    </book>
      |    <book id="bk102">
      |        <author>Ralls, Kim</author>
      |        <title>Midnight Rain</title>
      |        <genre>Fantasy</genre>
      |        <price>5.95</price>
      |        <publish_date>2000-12-16</publish_date>
      |        <description>
      |            A former architect battles corporate zombies, an evil sorceress, and her own childhood to become
      |            queen of the world.
      |        </description>
      |    </book>
      |    <book id="bk103">
      |        <author>Corets, Eva</author>
      |        <title>Maeve Ascendant</title>
      |        <genre>Fantasy</genre>
      |        <price>5.95</price>
      |        <publish_date>2000-11-17</publish_date>
      |        <description>
      |            After the collapse of a nanotechnology
      |            society in England, the young survivors lay the
      |            foundation for a new society.
      |        </description>
      |    </book>
      |    <book id="bk104">
      |        <author>Corets, Eva</author>
      |        <title>Oberon's Legacy</title>
      |        <genre>Fantasy</genre>
      |        <price>5.95</price>
      |        <publish_date>2001-03-10</publish_date>
      |        <description>In post-apocalypse England, the mysterious
      |            agent known only as Oberon helps to create a new life
      |            for the inhabitants of London. Sequel to Maeve
      |            Ascendant.
      |        </description>
      |    </book>
      |    <book id="bk111">
      |        <author>O'Brien, Tim</author>
      |        <title>MSXML3: A Comprehensive Guide</title>
      |        <genre>Computer</genre>
      |        <price>36.95</price>
      |        <publish_date>2000-12-01</publish_date>
      |        <description>The Microsoft MSXML3 parser is covered in
      |            detail, with attention to XML DOM interfaces, XSLT processing,
      |            SAX and more.
      |        </description>
      |    </book>
      |</catalog>
    """.stripMargin

  val booksCSV =
    """"@id",author,title
      |bk101,"Gambardella, Matthew",XML Developer's Guide
      |bk111,"O'Brien, Tim",MSXML3: A Comprehensive Guide""".stripMargin
}
