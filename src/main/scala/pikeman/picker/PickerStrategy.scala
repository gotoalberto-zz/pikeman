package pikeman.picker

trait PickerStrategy {

  def pick(): Int
  def learn(feedback: Feedback): Unit
}
