package parser

case class Location(input: String, offset: Int = 0):
  lazy val line = 1 + input.slice(0, offset + 1).count(_ == '\n')

  lazy val col = input.slice(0, offset + 1).lastIndexOf('\n') match
    case -1        => offset + 1
    case lineStart => offset - lineStart

  def advanceBy(n: Int): Location =
    copy(offset = offset + n)

  def remaining: String =
    input.substring(offset)

  def slice(n: Int): String =
    input.substring(offset, offset + n)
