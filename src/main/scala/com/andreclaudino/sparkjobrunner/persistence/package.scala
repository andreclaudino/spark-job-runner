package com.andreclaudino.sparkjobrunner

import org.apache.spark.sql.SparkSession
import org.apache.hadoop.fs.Path
import org.apache.hadoop.shaded.org.jline.utils.InputStreamReader
import java.nio.charset.StandardCharsets
import java.io.StringWriter
import scala.language.postfixOps
import scala.collection.parallel.immutable.ParSeq
import com.andreclaudino.sparkjobrunner.parameters.FileType
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.SaveMode


package object persistence {
    val logger = LoggerUtils.loadLogger()

    def loadSqlTransformer(sourcePath: String)(implicit spark: SparkSession): String = {
        val s3Path: Path = new Path(sourcePath)
        val fileSystem = s3Path.getFileSystem(spark.sparkContext.hadoopConfiguration)
        val file = fileSystem.open(s3Path)
        val reader = new InputStreamReader(file, StandardCharsets.UTF_8)
        val buffer = new StringWriter()

        Iterator
            .continually(reader.read)
            .takeWhile(-1 !=)
            .foreach(buffer.write)

        val sqlTransfomerScript = buffer.toString

        println("SQL transformer")
        println(sqlTransfomerScript)

        sqlTransfomerScript
    }

    def loadSourceData(sourcePaths: ParSeq[String], basePaths: ParSeq[String], format: FileType.Value)
                        (implicit spark: SparkSession): Unit = {
        import spark.implicits._
        
        sourcePaths.zipWithIndex.foreach {
            case (sourcePath, index) =>
            logger.info(s"Loading file $sourcePath")
            val dataFrame = format match {
                case FileType.Parquet => spark.read.parquet(sourcePath)
                case FileType.JSON => spark.read.json(sourcePath)
                case FileType.CSV => spark.read.csv(sourcePath)
            }
            dataFrame.createOrReplaceTempView(s"data_source$index")
            if (index == 0) {
                dataFrame.createOrReplaceTempView(s"data_source")
            }
        }

    }

    def saveTransformedDataFrame(outputPath: String, outputFormat: FileType.Value, partitions: Seq[String],
                                dataFrame: DataFrame): Unit = {
        val sparkWriter = dataFrame.write.mode(SaveMode.Append)
        val finalWriter = if (partitions.nonEmpty) sparkWriter.partitionBy(partitions: _*) else sparkWriter
        outputFormat match {
        case FileType.Parquet => finalWriter.parquet(outputPath)
        case FileType.JSON => finalWriter.json(outputPath)
        case FileType.CSV => finalWriter.csv(outputPath)
        }
    }
}
