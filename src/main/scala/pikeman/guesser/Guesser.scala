package pikeman.guesser

import java.io.{BufferedReader, PrintWriter, Reader, Writer}

class Guesser(strategy: GuesserStrategy, reader: Reader, writer: Writer) {

  private val output = new PrintWriter(writer)
  private val input = new BufferedReader(reader)

  def run(): Unit = {
    var feedback: Option[Feedback] = None
    do {
      output.println(strategy.guess())
      feedback = Option(input.readLine()).flatMap(parseFeedback)
    } while(feedback.nonEmpty)
  }

  def parseFeedback(line: String): Option[Feedback] = line match {
    case "-" => Some(Greater)
    case "+" => Some(Lower)
    case "<>" => Some(NotGuessed)
    case _ =>
      output.println(s"unexpected input: '$line'")
      None
  }
}
