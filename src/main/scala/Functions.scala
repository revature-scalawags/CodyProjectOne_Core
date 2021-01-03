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
    */
  def start() {
    println("\nWelcome to Cody Piazza's Project One!") // Welcome statement
    Thread.sleep(2000) // Gives user time to see welcome statement
    println(
      "\nThis project will analyze the most popular links clicked on Wikipedia pages in 2020.\n"
    ) // States purpose of program
    Thread.sleep(2000) // Gives user time to see above message
    promptUser()
  }

  /**
    * Explains input options to the user, then runs the relevant hive query on the hadoop cluster based on user input
    */
  def promptUser() {
    breakable {
      while (true) {
        println(
          "Please input one of the following options to perform an analysis for the specified month of 2020 (or the entire year):\n"
        )
        println("A standard analysis will find the most popular Wikipedia links that were clicked on, based on the following format:")
        println("[Page where the link was present] to [Page that the link took the user to]\n")
        println("If you only wish too see what page a user was on when they clicked a link, you may add the \"-from\" flag (not supported for \"2020\" command).")
        println("If you wish to see which pages were navigated to, regardless of the page that they came from, you may add the \"-to\" flag (not supported for \"2020\" command).\n")
        println("[1] January\t [2] February\t [3] March\t [4] April")
        println("[5] May\t\t [6] June\t [7] July\t [8] August")
        println("[9] September\t [10] October\t [11] November\t [12] December")
        println("[2020] January-December 2020")
        println("[q] Exit application\n")
        val input = readLine()
        println()
        val inputArr = input.split(" ").filter(_.length > 0)
        if (inputArr.length == 0 || inputArr.length > 2) invalid
        else if (
          inputArr.length == 2 && !inputArr(1).equals("-from") && !inputArr(1)
            .equals("-to")
        ) invalid
        else {
          var flag = ""
          if (inputArr.length == 2) flag = inputArr(1).substring(1)
          try {
            inputArr(0) match {
              case "1"    => Hive.query("january" + flag)
              case "2"    => Hive.query("february" + flag)
              case "3"    => Hive.query("march" + flag)
              case "4"    => Hive.query("april" + flag)
              case "5"    => Hive.query("may" + flag)
              case "6"    => Hive.query("june" + flag)
              case "7"    => Hive.query("july" + flag)
              case "8"    => Hive.query("august" + flag)
              case "9"    => Hive.query("september" + flag)
              case "10"   => Hive.query("october" + flag)
              case "11"   => Hive.query("november" + flag)
              case "12"   => Hive.query("december" + flag)
              case "2020" => {
                if (inputArr.length == 2) invalid
                else Hive.query("2020" + flag)
              }
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
}
