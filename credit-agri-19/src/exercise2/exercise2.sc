class RunExercise {

  def contestResponse() {
    for (ln <- scala.io.Source.stdin.getLines) {
      val line = ln
      /* Lisez les données et effectuez votre traitement */
    }
  }

  def digitSum(m: Int): Int = {
    if (m < 10) {
      m
    } else {
      m % 10 + digitSum(m / 10)
    }
  }

  def lucky(n: Int): Int = {
    if (n <= 99) {
      n
    } else {
      lucky(digitSum(n))
    }
  }

  def isLucky(n: Int): Boolean = lucky(n) == 42

  def choco(input: List[String]): Int = {
    input.tail.map(x => isLucky(x.toInt)).count(x => x)
  }

}

import $ivy.`org.scalatest::scalatest:3.0.8`, org.scalatest.FunSuite
import $ivy.`com.lihaoyi::ammonite-ops:1.6.9`, ammonite.ops._

val wd = pwd/"test"

class TestExercise extends FunSuite {

  (1 to 6).map(i => (s"input$i.txt", s"output$i.txt")).foreach { t =>
    test(s"Test $t") {
      val input = read.lines(wd / t._1).toList
      val output = read.lines(wd / t._2).head.toInt

      val r = new RunExercise()

      assert(r.choco(input) == output)
    }
  }

}

(new TestExercise).execute()