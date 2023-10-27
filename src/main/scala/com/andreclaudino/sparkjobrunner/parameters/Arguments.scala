package com.andreclaudino.sparkjobrunner.parameters

import scopt.OptionParser

import FileType._
import constants._

case class Arguments (
  sourcePaths: Seq[String] = Seq(),
  basePaths: Seq[String] = Seq(),
  sourceFileTypeName: FileType = Parquet,
  
  outputPath: String = new String(),
  outputFileTypeName: FileType = FileType.Parquet,

  sqlTransformerPath: String = new String(),

  partitionColumnsValue: Option[Seq[String]] = None,
  
  sparkMasterUri: Option[String] = Some(DEFAULT_SPARK_MASTER)
)
