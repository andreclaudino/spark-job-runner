package com.andreclaudino.sparkjobrunner
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory


object LoggerUtils {
    def loadLogger(): Logger = {
        val factory = LoggerFactory
            .getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME)
        val logger = Logger(factory)

        logger
    }
}
