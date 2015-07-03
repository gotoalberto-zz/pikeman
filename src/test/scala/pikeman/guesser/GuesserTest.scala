package pikeman.guesser

import java.io.{StringReader, StringWriter}

import org.scalatest._

class GuesserTest extends FlatSpec with ShouldMatchers {

  "A guesser" should "provide an initial number" in new Fixture {
    val input = new StringReader("")
    val guesser = new Guesser(strategy, input, output)
    guesser.run()
    output.toString shouldBe "1\n"
  }

  it should "receive feedback" in new Fixture {
    val input = new StringReader("+\n-\n")
    val guesser = new Guesser(strategy, input, output)
    guesser.run()
    output.toString shouldBe "1\n2\n3\n"
  }

  it should "show a message when it doesn't receive valid feedback" in new Fixture {
    val invalidFeedback = "x"
    val input = new StringReader(s"+\n-\n$invalidFeedback\n")
    val guesser = new Guesser(strategy, input, output)
    guesser.run()
    output.toString shouldBe s"1\n2\n3\nunexpected input: '$invalidFeedback'\n"
  }

  it should "learn from received feedback" in new Fixture {
    val input = new StringReader("+\n-\n=\n<>\n")
    val guesser = new Guesser(strategy, input, output)
    guesser.run()
    strategy.feedback shouldBe Seq(Greater, Lower, Guessed, NotGuessed)
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
