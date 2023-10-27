package com.andreclaudino.sparkjobrunner.parameters

object FileType extends Enumeration {
  type FileType = Value
  val Parquet, CSV, JSON = Value
}