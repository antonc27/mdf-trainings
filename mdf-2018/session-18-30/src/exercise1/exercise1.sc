class RunExercise {

  def contestResponse() = {
    print(alertTime(scala.io.Source.stdin.getLines.toList))
  }

  def alertTime(lines: List[String]): Int = {

    val N = lines.head.toInt
    val limit = 10 * lines.tail.head.toInt

    def minutes(str: String): Int = {
      val q = str.split(':')
      q.head.toInt * 60 + q.tail.head.toInt
    }

    def parse(str: String): (Int, Int) = {
      val p = str.split(' ')
      val ch = p.tail.head
      (minutes(p.head), if (ch == "E") {1} else {-1})
    }

    var totalAlertTime = 0

    var current = 0
    var isAlerting = false

    //var lastAlertTime = 0
    var lastTime = 0

    var delta = 0

    val (firstTime, inc) = parse(lines.tail.tail.head)

    current += inc
    delta = 0
    lastTime = firstTime

    val changes = lines.tail.tail.tail :+ "23:00 S"
    for (change <- changes) {
      val (currTime, currInc) = parse(change)

      current += currInc
      delta = currTime - lastTime

      if (isAlerting) {
        totalAlertTime += delta
      }

      if (current > limit) {
        isAlerting = true
      } else {
        isAlerting = false
      }

      lastTime = currTime
    }

    totalAlertTime
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

      assert(r.alertTime(input) == output)
    }
  }

}

(new TestExercise).execute()