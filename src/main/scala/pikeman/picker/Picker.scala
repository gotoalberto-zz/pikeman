package pikeman.picker

import java.io.{BufferedReader, PrintWriter, Reader, Writer}

class Picker(strategy: PickerStrategy, reader: Reader, writer: Writer) {
  private val guessedAtRegex = """guessed at ([1-5])""".r
  private val output = new PrintWriter(writer)
  private val input = new BufferedReader(reader)

  def run(): Unit = {
    var feedback: Option[Feedback] = None
    do {
      output.println(strategy.pick())
      feedback = Option(input.readLine()).flatMap(parseFeedback)
      feedback.foreach(strategy.learn)
    } while (feedback.nonEmpty)
  }

  def parseFeedback(line: String): Option[Feedback] = line match {
    case "not guessed" => Some(NotGuessed)
    case guessedAtRegex(attempt) => Some(GuessedAt(attempt.toInt))
    case _ =>
      output.println(s"unexpected input: '$line'")
      None
  }
}
