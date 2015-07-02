package pikeman.guesser

import java.io.{StringReader, StringWriter}

import org.scalatest._

class GuesserTest extends FlatSpec with ShouldMatchers {

  "A guesser" should "provide a number and keep the prompt on hold for feedback" in new Fixture {
    val input = new StringReader("")
    val guesser = new Guesser(strategy, input, output)
    guesser.run()
    output.toString shouldBe "1\n"
  }

  trait Fixture {

    protected val output = new StringWriter

    protected val strategy = new GuesserStrategy {
      var nextPick = 0
      var feedback = Seq.empty[Feedback]

      override def guess(): Int = {
        nextPick += 1
        nextPick
      }

      override def learn(newFeedback: Feedback): Unit = {
        feedback :+= newFeedback
      }
    }
  }
}
