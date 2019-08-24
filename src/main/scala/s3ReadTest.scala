import Reddit_Data_Preload.{AwsS3Utils, SparkSessionWrapper}
import org.apache.spark.sql.functions.{col, count}

object s3ReadTest extends App with SparkSessionWrapper with AwsS3Utils {

  val bucketName = this.commentsS3Bucket

  val data = this.sparkSession.read
    .option("inferSchema", value = true)
    .option("header", value = true)
    .json(bucketName).toDF()

  data.printSchema()
  data.show()
  //  data.select(count(col("*"))).show()
  //  data.select("subreddit").distinct().show(false)

}
