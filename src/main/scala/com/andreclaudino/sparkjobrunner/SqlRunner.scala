package com.andreclaudino.dataengineering.parameters

import com.andreclaudino.sparkjobrunner.parameters.Arguments
import com.andreclaudino.sparkjobrunner.parameters.parameters.parseParameters
import com.andreclaudino.sparkjobrunner.LoggerUtils


object Main extends App {
  
  val logger = LoggerUtils.loadLogger()

  logger.info(s"Processing command line arguments")
  
  val arguments = parseParameters(args)

}