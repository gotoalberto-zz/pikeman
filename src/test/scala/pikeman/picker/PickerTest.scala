package pikeman.picker

import java.io.{StringReader, StringWriter}

import org.scalatest.{FlatSpec, ShouldMatchers}

class PickerTest extends FlatSpec with ShouldMatchers {

  "A picker" should "play just one round unless feedback is received" in new Fixture {
    val input = new StringReader("")
    val picker = new Picker(strategy, input, output)
    picker.run()
    output.toString should fullyMatch regex "\\d+\n"
  }

  it should "play two rounds if feedback is received" in new Fixture {
    val input = new StringReader("not guessed\n")
    val picker = new Picker(strategy, input, output)
    picker.run()
    output.toString.lines should have size 2
  }

  it should "play rounds as long as feedback is received" in new Fixture {
    val input = new StringReader("not guessed\nguessed at 4\nguessed at 2\n")
    val picker = new Picker(strategy, input, output)
    picker.run()
    output.toString.lines should have size 4
  }

  it should "use the strategy received as parameter" in new Fixture {
    val input = new StringReader("not guessed\nguessed at 1\nguessed at 5\n")
    val picker = new Picker(strategy, input, output)
    picker.run()
    output.toString shouldBe "1\n2\n3\n4\n"
    strategy.feedback shouldBe Seq(NotGuessed, GuessedAt(1), GuessedAt(5))
  }

  it should "stop on unexpected input" in new Fixture {
    val input = new StringReader("not guessed\ninvalid\nguessed at 5\n")
    val picker = new Picker(strategy, input, output)
    picker.run()
    output.toString.lines.toList.last shouldBe "unexpected input: 'invalid'"
  }

  trait Fixture {
    protected val output = new StringWriter
    protected val strategy = new PickerStrategy {
      var nextPick = 0
      var feedback = Seq.empty[Feedback]

      override def pick(): Int = {
        nextPick += 1
        nextPick
      }

      override def learn(newFeedback: Feedback): Unit = {
        feedback :+= newFeedback
      }
    }
  }
}
