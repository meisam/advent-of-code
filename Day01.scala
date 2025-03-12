package day01

import parser.StringParsers
import StringParsers.*

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
  val spaces = StringParsers.char(' ').many1
  val optSpaces = char(' ').many
  val lineParser: Parser[(Int, Int)] = ((optSpaces *> int) <* spaces) ** int <* (optSpaces ** char('\n'))
  lineParser.many.run(input) match
    case numberPairs: List[(Int, Int)] => process(numberPairs)
    case error @ parser.StackTrace(stack) =>
      stack.mkString("Error: <", "\n", ">")
