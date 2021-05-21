package com.astra.spark

import org.apache.spark.sql.SparkSession
import com.datastax.spark.connector._
import com.datastax.spark.connector.cql.CassandraConnector
import org.apache.spark.SparkConf

object Migration {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder
      .appName("DSE-ASTRA-MIGRATION")
      .enableHiveSupport()
      .getOrCreate()

    val sc = spark.sparkContext

    // Set Up Astra Spark Conf
    val sparkConfAstra = new SparkConf(true)
    sparkConfAstra.setAppName("DSE-ASTRA-MIGRATION")
    sparkConfAstra.set("spark.cassandra.connection.config.cloud.path", "secure-connect-spark-test.zip")
    sparkConfAstra.set("spark.cassandra.auth.username", "CwNWQvyqPqngorokLrfPbnPF")
    sparkConfAstra.set("spark.cassandra.auth.password", "3LskpjRKmt8wtbIejPdRSZ0,WWol.K.Irjr26++AAPoMihBg5PCbf8gmh-nAFwfxmM5SO7a91ZPeRymZQuL,KjNr_uyayRs_ZMX-4yr.CZJj3cge0AyuofCQCJlYZAFO")
    sparkConfAstra.set("spark.dse.continuousPagingEnabled", "false")
    sparkConfAstra.set("spark.cassandra.connection.localDC", "westus2")

    // set value for keyspace and table
    val keyspace = "test_spark_migration"
    val table = "data_table"

    println("Job Running")

    val source = sc.cassandraTable(s"$keyspace", s"$table")

    {
      //set connector as Astra instead of default DSE
      implicit val c = CassandraConnector(sparkConfAstra)

      //Save DSE data to Astra
      source.saveToCassandra(s"$keyspace", s"$table")
    }

    println("Job Complete")

    spark.stop()
    sys.exit(0)
  }
}
