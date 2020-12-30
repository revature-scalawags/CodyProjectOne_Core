import scala.util.control.Breaks._
import com.typesafe.scalalogging.LazyLogging

object Functions extends LazyLogging {

  /** Simple function called when user's input is invalid.
    */
  def invalid() {
    println("Invalid input! Try again.")
    waitforUser()
  }

  /** Simple function that causes program to pause while user looks at results
    */
  def waitforUser() {
    println("\nPress enter to resume")
    readLine()
  }

  /** Function used to start the application.
    * -
    * -
    */
  def start() {
    println("\nWelcome to Cody Piazza's project One!") // Welcome statement
    Thread.sleep(2000) // Gives user time to see welcome statement
    println(
      "\nThis project will analyze the most popular links clicked on Wikipedia pages in 2020.\n"
    ) // States purpose of program
    Thread.sleep(2000) // Gives user time to see above message
    promptUser()
  }

  def promptUser() {
    breakable {
      while (true) {
        println(
          "Please input one of the following options to perform the analysis for the specified month of 2020 (or the entire year):\n"
        )
        println("[1] January\t [2] February\t [3] March\t [4] April")
        println("[5] May\t\t [6] June\t [7] July\t [8] August")
        println("[9] September\t [10] October\t [11] November\t [12] December")
        println("[q] Exit application\n")
        val desiredAnalysis = readLine()
        println()
        try {
          desiredAnalysis match {
            case "1"    => Hive.query("january")
            case "2"    => Hive.query("february")
            case "3"    => Hive.query("march")
            case "4"    => Hive.query("april")
            case "5"    => Hive.query("may")
            case "6"    => Hive.query("june")
            case "7"    => Hive.query("july")
            case "8"    => Hive.query("august")
            case "9"    => Hive.query("september")
            case "10"   => Hive.query("october")
            case "11"   => Hive.query("november")
            case "q"    => { println("\nExiting...\n"); break }
            case _      => invalid
          }
        } catch {
          case n: NumberFormatException => invalid
        }
      }
    }
  }
}
