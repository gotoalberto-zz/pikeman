package pikeman.guesser

trait GuesserStrategy {

  def guess(): Int
  def learn(feedback: Feedback): Unit
}