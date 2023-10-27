scalaVersion := "2.13.8"

name := "spark-job-runner"
organization := "com.andreclaudino"
version := "1.0.0"

val sparkVersion = "3.5.0"

libraryDependencies ++= Seq(
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "ch.qos.logback" % "logback-classic" % "1.4.11",
    
    "com.github.scopt" %% "scopt" % "4.0.0",

    "org.apache.spark" %% "spark-core" % "3.5.0",
    "org.apache.spark" %% "spark-sql" % "3.5.0",
    "org.apache.spark" %% "spark-mllib" % "3.5.0",
)


assembly / assemblyJarName := "spark-job-runner.jar"
