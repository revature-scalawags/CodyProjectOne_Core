import scala.sys.process._
import scala.io.Source
import com.typesafe.scalalogging.LazyLogging
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.PrintWriter

object Hive extends LazyLogging {
  def query(month: String) {
    if (month == "") {
        val f = new PrintWriter("results.txt")
        f.print("!!")
        f.close()
      //FileUtils.deleteQuietly(f)
      println("Invalid input!")
    }
    else {
      val f = new File("results.txt")
      FileUtils.deleteQuietly(f)
      val sql =
        s"ssh root@sandbox-hdp.hortonworks.com -p 2222 hive -S -f ~/queries/$month.sql > ./results.txt "
      val getResults =
        "scp -P 2222 root@sandbox-hdp.hortonworks.com:/root/results.txt ./"
      val linkMonth = s"LINKS NAVIGATED IN ${month.toUpperCase()}"
      sql.!
      getResults.!
      println("_" * 114)
      println
      println((" " * 42) + linkMonth + (" " * (44 - month.length)) + "COUNT")
      println("_" * 114)
      println("-" * 114)
      for (line <- Source.fromFile("results.txt").getLines) {
        val newline = line.replaceAll("\t", " ").trim

        val category = newline.substring(0, newline.lastIndexOf(" "))
        val count = newline.substring(newline.lastIndexOf(" ") + 1)
        val s1 = " " * (100 - category.length)
        val s2 = " " * (7 - count.length)
        println(s"| $category$s1 | $count$s2 |")
        println("-" * 114)
      }
      logger.debug("Data returned successfully!")
      println()
    }
  }
}
