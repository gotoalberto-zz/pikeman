package pikeman.picker

import org.scalatest._

class DumbPickerStrategyTest extends FlatSpec with ShouldMatchers {

  "A DumbPickerStrategy" should "pick always the same number" in {
    new DumbPickerStrategy(42).pick() shouldBe 42
    new DumbPickerStrategy(13).pick() shouldBe 13
  }

  it should "learn nothing" in {
    val number = 42
    val strategy = new DumbPickerStrategy(number)

    strategy.learn(NotGuessed)
    strategy.pick() shouldBe number

    strategy.learn(GuessedAt(1))
    strategy.pick() shouldBe number
  }
}
