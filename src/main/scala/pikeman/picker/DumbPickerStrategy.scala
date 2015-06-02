package pikeman.picker

class DumbPickerStrategy(number: Int) extends PickerStrategy {

  override def pick(): Int = number

  override def learn(feedback: Feedback): Unit = {}
}
