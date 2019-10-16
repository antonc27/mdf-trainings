class RunExercise {

  def contestResponse() {
    for (ln <- scala.io.Source.stdin.getLines) {
      val line = ln
      /* Lisez les données et effectuez votre traitement */
    }
  }

  def password(input: List[String]): Int = {
    val a = input.head.toInt
    val b = input.tail.head.toInt
    val d = input.tail.tail.head.toInt

    for (i <- a to b) {
      if (i % d == 0) {
        return i
      }
    }

    -1
  }

}

import $ivy.`org.scalatest::scalatest:3.0.8`, org.scalatest.FunSuite
import $ivy.`com.lihaoyi::ammonite-ops:1.6.9`, ammonite.ops._

val wd = pwd/"test"

class TestExercise extends FunSuite {

  (1 to 4).map(i => (s"input$i.txt", s"output$i.txt")).foreach { t =>
    test(s"Test $t") {
      val input = read.lines(wd / t._1).toList
      val output = read.lines(wd / t._2).head.toInt

      val r = new RunExercise()

      assert(r.password(input) == output)
    }
  }

}

(new TestExercise).execute()