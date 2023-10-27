package com.andreclaudino.sparkjobrunner.parameters

object constants {
    val SOURCE_PATH_HELP = "Comma-separated file or folder list to be read"
    val BASE_PATH_HELP = "Spark data source base paths separated by commas"
    val SOURCE_FILE_TYPE_HELP = "Source file type"
    val OUTPUT_PATH = "The output path where the partitioned files will be saved"
    val OUTPUT_FILE_TYPE_HELP = "Output file type"
    val PARTITION_COLUMNS_HELP = "Comma-separated list of partitions columns on output dataset"
    val SQL_TRANSFORMER_PATH_HELP = "A Spark-SQL file to transform the data"   
    
    val DEFAULT_FILE_TYPE = FileType.Parquet

    val DEFAULT_SPARK_MASTER = "k8s://https://kubernetes.default.svc"
    val SPARK_MASTER_HELP = "Spark master URI"
}
