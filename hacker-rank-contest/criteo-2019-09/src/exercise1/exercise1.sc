import java.io.PrintWriter

object Solution {

  /*
   * Complete the indianJob function below.
   */
  def indianJob(g: Int, arr: Array[Int]): String = {
    val A = arr.sum

    if (2*g < A) {
      return "NO"
    } else if (A <= g) {
      return "YES"
    }

    val q = Array.ofDim[Boolean](arr.length, g+1)

    def getQ(i: Int, j: Int): Boolean =
      if (j < 0 || j > g)
        false
      else
        q(i)(j)

    q(0)(arr.head) = true

    for (i <- 1 until arr.length; j <- 0 to g) {
      q(i)(j) = getQ(i-1, j) || (arr(i) == j) || getQ(i-1, j-arr(i))
      if (A-g <= j && q(i)(j)) {
        return "YES"
      }
    }

    return "NO"
  }

  def main(args: Array[String]) {
    val stdin = scala.io.StdIn

    val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val t = stdin.readLine.trim.toInt

    for (tItr <- 1 to t) {
      val ng = stdin.readLine.split(" ")

      val n = ng(0).trim.toInt

      val g = ng(1).trim.toInt

      val arr = stdin.readLine.split(" ").map(_.trim.toInt)

      val result = indianJob(g, arr)

      printWriter.println(result)
    }

    printWriter.close()
  }
}

import $ivy.`org.scalatest::scalatest:3.0.8`, org.scalatest.FunSuite
import $ivy.`com.lihaoyi::ammonite-ops:1.6.9`, ammonite.ops._

val wd = pwd/"test"

class TestExercise extends FunSuite {

  (0 to 11).map(i => (s"input${if (i < 10) s"0$i" else s"1${i % 10}"}.txt",
    s"output${if (i < 10) s"0$i" else s"1${i % 10}"}.txt")).foreach { tt =>
    test(s"Test $tt") {
      var input = read.lines(wd / tt._1).toList
      val output = read.lines(wd / tt._2).toArray

      val s = Solution

      val t = input.head.trim.toInt
      input = input.tail

      for (tItr <- 1 to t) {
        val ng = input.head.split(" ")
        input = input.tail

        val n = ng(0).trim.toInt

        val g = ng(1).trim.toInt

        val arr = input.head.split(" ").map(_.trim.toInt)
        input = input.tail

        //println(s"Iter $tItr, n: $n, g: $g")

        val result = s.indianJob(g, arr)

        assert(result == output(tItr - 1), s"n: $n, g: $g, arr: [${arr.mkString(",")}]")
      }


    }
  }

}

(new TestExercise).execute()