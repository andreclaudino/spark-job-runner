package com.andreclaudino.sparkjobrunner

import org.apache.spark.sql.SparkSession
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory


package object sparkSessionUtils {
    

    val logger = LoggerUtils.loadLogger()

    def loadSparkSession(sparkMasterUri: String): SparkSession = {
        
        val sparkSession = SparkSession
            .builder()
            .master(sparkMasterUri)
            .config("spark.sql.sources.useV1SourceList", "false")
            .getOrCreate()

        reportSparkConfig(sparkSession)

        sparkSession
    }

    def reportSparkConfig(sparkSession: SparkSession) = {
        logger.info(s"Current spark configurations are:")
        
        val configurations = sparkSession.sparkContext.getConf.getAll.foreach {
            case (key, value) => {
                logger.info(s"$key: $value")
            }
        }
    }
}
