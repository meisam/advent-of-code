package day03

import parser.TextParsers
import TextParsers.*

opaque type Error = String

def process(input: List[(Int, Int)]): Int =
  val (firsts, seconds) = input.unzip
  firsts
    .sorted()
    .zip(seconds.sorted())
    .map(_ - _)
    .map(math.abs)
    .sum

def parse(input: String): Error | Int =
  // integer, followed by (ignored) space, followed by another integer
  val spaces = TextParsers.char(' ').many1
  val optSpaces = char(' ').many
  val lineParser: Parser[(Int, Int)] = ((optSpaces *> int) <* spaces) ** int <* (optSpaces ** char('\n'))
  lineParser.many.run(input) match
    case error @ parser.ParseError(stack) =>
      stack.mkString("Error: <", "\n", ">")
    case numberPairs: List[(Int, Int)] => process(numberPairs)
