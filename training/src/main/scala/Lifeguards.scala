import java.io._

class RunExercise {

  def contestResponse() = {
    print(alertTime(io.Source.stdin.getLines.toList))
  }

  def alertTime(lines: List[String]): Int = {
    //val lines = io.Source.stdin.getLines.toList

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