package pikeman.guesser

sealed trait Feedback

case object Greater extends Feedback
case object Lower extends Feedback
case object NotGuessed extends Feedback
case object Guessed extends Feedback
