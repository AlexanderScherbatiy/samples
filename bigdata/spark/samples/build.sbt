name := "spark-xml"

version := "0.1"

scalaVersion := "2.10.6"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.6.1",
  "org.apache.spark" %% "spark-sql" % "1.6.1"
)

libraryDependencies += "com.databricks" %% "spark-csv" % "1.5.0"
libraryDependencies += "com.databricks" % "spark-xml_2.10" % "0.3.5"
libraryDependencies += "org.scalatest" % "scalatest_2.10" % "3.0.1" % "test"
libraryDependencies += "junit" % "junit" % "4.12" % "test"
