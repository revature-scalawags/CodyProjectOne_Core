import scala.sys.process._
import scala.io.Source
import com.typesafe.scalalogging.LazyLogging
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.PrintWriter

object Hive extends LazyLogging {

  /**
    * - Connects to the VM running the Hadoop cluster and runs a Hive query on that cluster based on what month name is passed in.
    * - Writes the results of the Hive query to a text document
    * - Reads from the text document and prints out the results to the user.
    * @param month Used to decide which Hive query to perform
    */
  def query(month: String) {
    if (month == "") {
      println("Invalid input!")
    }
    else {
      val p = new PrintWriter("results.txt")
      p.write("")
      p.close()
      val sql = s"ssh root@sandbox-hdp.hortonworks.com -p 2222 hive -S -f ~/queries/$month.sql > ./results.txt"
      val getResults ="scp -P 2222 root@sandbox-hdp.hortonworks.com:/root/results.txt ./"
      var linkMonth = s"LINKS NAVIGATED IN ${month.toUpperCase()}"
      if (month.substring(month.length-4).equals("from")) linkMonth = s"LINKS NAVIGATED FROM IN ${month.substring(0, month.length - 4).toUpperCase()}"
      else if (month.substring(month.length-2).equals("to")) linkMonth = s"LINKS NAVIGATED TO IN ${month.substring(0, month.length - 2).toUpperCase()}"
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
