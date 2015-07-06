package pikeman.guesser.strategy

import org.scalatest._
import pikeman.GameNumbers
import pikeman.guesser._

class BinaryGuesserStrategyTest extends FlatSpec with ShouldMatchers {

  "A Binary Guesser" should "guess 50 initially" in new Fixture {
    guesser.guess shouldBe 50
  }

  it should "guess 75 if 50 is too low" in new Fixture {
    guesser.learn(Greater)
    guesser.guess shouldBe 75
  }

  it should "guess 25 if 50 is too high" in new Fixture {
    guesser.learn(Lower)
    guesser.guess shouldBe 25
  }

  it should "guess 62 if 50 is too low but 75 too high" in new Fixture {
    guesser.learn(Greater)
    guesser.learn(Lower)
    guesser.guess shouldBe 62
  }

  it should "guess 50 after finding the number" in new Fixture {
    guesser.learn(Greater)
    guesser.learn(Guessed)
    guesser.guess shouldBe 50
  }

  it should "guess 50 after not finding the number" in new Fixture {
    guesser.learn(Greater)
    guesser.learn(NotGuessed)
    guesser.guess shouldBe 50
  }

  it should "throw if 1 is too high" in new Fixture {
    while (guesser.guess > GameNumbers.Min) {
      guesser.learn(Lower)
    }
    an [IllegalArgumentException] shouldBe thrownBy {
      guesser.learn(Lower)
    }
  }

  it should "throw if 100 is too low" in new Fixture {
    while (guesser.guess < GameNumbers.Max) {
      guesser.learn(Greater)
    }
    an [IllegalArgumentException] shouldBe thrownBy {
      guesser.learn(Greater)
    }
  }

  trait Fixture {
    val guesser = new BinaryGuesserStrategy
  }
}
