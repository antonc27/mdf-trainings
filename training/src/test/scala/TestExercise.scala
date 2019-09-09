import org.scalatest.FunSuite
import scala.io.Source

class TestExercise extends FunSuite {

  (1 to 6).map(i => (s"input$i.txt", s"output$i.txt")).foreach { t =>
    test(s"Test $t") {
      val input = Source.fromResource(t._1).getLines.toList
      val output = Source.fromResource(t._2).getLines().next().toInt

      val r = new RunExercise()

      assert(r.alertTime(input) == output)
    }
  }

}
