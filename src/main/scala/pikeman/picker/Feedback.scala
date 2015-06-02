package pikeman.picker

sealed trait Feedback

case object NotGuessed extends Feedback
case class GuessedAt(attempt: Int) extends Feedback
