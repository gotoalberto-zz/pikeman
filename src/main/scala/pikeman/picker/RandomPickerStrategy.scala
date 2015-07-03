package pikeman.picker

class RandomPickerStrategy extends PickerStrategy {

  private val randomPicker = scala.util.Random

  override def pick(): Int = randomPicker.nextInt()

  override def learn(feedback: Feedback): Unit = {}
}
