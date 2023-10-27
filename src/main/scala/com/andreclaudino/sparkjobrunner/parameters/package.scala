package com.andreclaudino.sparkjobrunner.parameters

import constants._
import FileType._

package object parameters {
  implicit val FileTypeRead: scopt.Read[FileType.Value] = scopt.Read.reads(FileType withName _)


  import scopt.OParser

  private val builder = OParser.builder[Arguments]

  private val argumentsParser = {
    import builder._

    OParser.sequence(
      programName("spark-job-runner"),
      head("spark-job-runner", "1.x"),

      opt[Seq[String]]('s', "source-paths")
        .text(SOURCE_PATH_HELP)
        .required()
        .action((x, c) => c.copy(sourcePaths = x)),

      opt[Seq[String]]('b', "base-paths")
        .text(BASE_PATH_HELP)
        .required()
        .action((x, c) => c.copy(basePaths = x)),

      opt[FileType]('t', "source-file-type")
        .text(SOURCE_FILE_TYPE_HELP)
        .action((x, c) => c.copy(sourceFileTypeName = x)),

      opt[String]("sql-transformer-path")
        .text(SQL_TRANSFORMER_PATH_HELP)
        .required()
        .action((x, c) => c.copy(sqlTransformerPath = x)),

      opt[String]("output-path")
        .text(OUTPUT_PATH)
        .required()
        .action((x, c) => c.copy(outputPath = x)),

      opt[FileType]('o', "output-file-type")
        .text(SOURCE_FILE_TYPE_HELP)
        .action((x, c) => c.copy(outputFileTypeName = x)),

      opt[Option[Seq[String]]]("partition-columns")
        .text(PARTITION_COLUMNS_HELP)
        .action((x, c) => c.copy(partitionColumnsValue = x)),
      
      opt[Option[String]]("spark-master")
        .text(SPARK_MASTER_HELP)
        .action((x, c) => c.copy(sparkMasterUri = x)),
    )
  }

  def parseParameters(args: Array[String]): Option[Arguments] = {
    import scopt.{ OParserSetup, DefaultOParserSetup }

    val setup: OParserSetup = new DefaultOParserSetup {
      override def showUsageOnError = Some(true)
    }
    OParser.parse(argumentsParser, args, Arguments())
  }
}
