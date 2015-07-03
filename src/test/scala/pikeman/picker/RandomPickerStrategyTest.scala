package pikeman.picker

import org.scalatest._

class RandomPickerStrategyTest extends FlatSpec with ShouldMatchers {

  "A Random Picker strategy" should "pick random numbers" in {
    val randomStrategy = new RandomPickerStrategy()
    Seq.fill(10)(randomStrategy.pick()).toSet.size shouldBe > (1)
  }
}
