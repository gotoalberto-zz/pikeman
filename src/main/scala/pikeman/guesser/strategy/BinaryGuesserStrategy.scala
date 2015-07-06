package pikeman.guesser.strategy

import pikeman.GameNumbers
import pikeman.guesser._

class BinaryGuesserStrategy extends GuesserStrategy {

  private var min = GameNumbers.Min
  private var max = GameNumbers.Max

  override def guess: Int = (min + max) / 2

  override def learn(feedback: Feedback): Unit = {
    feedback match {
      case Lower =>
        require(guess > GameNumbers.Min, "cannot go below 1")
        max = guess - 1
      case Greater =>
        require(guess < GameNumbers.Max, "cannot go over 100")
        min = guess + 1
      case Guessed | NotGuessed =>
        min = GameNumbers.Min
        max = GameNumbers.Max
    }
  }
}
