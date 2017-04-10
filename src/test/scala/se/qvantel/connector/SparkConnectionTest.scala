package se.qvantel.connector

import com.datastax.spark.connector.embedded.EmbeddedCassandra
import com.datastax.spark.connector.japi.RDDAndDStreamCommonJavaFunctions
import com.datastax.spark.connector.util.Logging
import com.holdenkarau.spark.testing.SharedSparkContext
import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.{BeforeAndAfterAll, FunSuite}
import org.scalatest.concurrent.Eventually

import scala.collection.mutable

class SparkConnectionTest extends CassandraEmbedded with Eventually{

  var sc: SparkContext = null


    val name = "sparkTtest"

    val conf = new SparkConf()
      .setAppName(name)
      .setMaster("local")
      .set("spark.default.parallelism", "1")

    sc = new SparkContext(conf)
    sc.stop()

  test("spark Configuration testing"){
    val appname = "sparkConfTest"
    // create spark config
    val sparkConf = new SparkConf().setAppName(appname).setMaster("local")
    val sparkContx = new SparkContext(sparkConf)
    assert(sparkContx != null)


    println("------------->"+ sparkContx.getRDDStorageInfo.toString() +"<----------")

    sparkContx.stop()
  }

  test("spark point to cassandra"){
    val appname = "sparkConfTest"

    // create spark config
    val sparkConf = new SparkConf().setAppName(appname).setMaster("local")
    sparkConf.set("spark.cassandra.connection.host", "1")
    val sparkContx = new SparkContext(sparkConf)
    assert(sparkContx != null)


    println("------------->"+ sparkContx.toString() +"<----------")

    sparkContx.stop()
  }

  override def clearCache(): Unit = ???

}