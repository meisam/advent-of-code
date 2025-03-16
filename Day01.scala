package day01

import parser.StringParsers
import StringParsers.*

opaque type Error = String

val inputParser = {
  val spaces = StringParsers.char(' ').many1
  val optSpaces = char(' ').many
  val lineParser: Parser[(Int, Int)] =
    // integer, followed by (ignored) space, followed by another integer
    ((optSpaces *> int) <* spaces) ** int <* (optSpaces ** opt(char('\n')))
  lineParser.many
}

def listDistance(input: List[(Int, Int)]): Int =
  val (firsts, seconds) = input.unzip
  firsts
    .sorted()
    .zip(seconds.sorted())
    .map(_ - _)
    .map(math.abs)
    .sum

def parse(input: String): Error | Int =
  inputParser.run(input) match
    case numberPairs: List[(Int, Int)] => listDistance(numberPairs)
    case error @ parser.StackTrace(stack) =>
      stack.mkString("Error: <", "\n", ">")

def similarityScore(input: String): Error | Int =
  inputParser.run(input) match
    case numberPairs: List[(Int, Int)] =>
      val (firsts, seconds) = numberPairs.unzip
      val firstCounts = firsts.groupMapReduce(identity)(_ => 1)(_ + _).toMap
      val secondCounts = seconds.groupMapReduce(identity)(_ => 1)(_ + _).toMap
      firstCounts.foldLeft(0):
        case (accum, (element, elementCount)) =>
          accum + element * elementCount * secondCounts.getOrElse(element, 0)
    case error @ parser.StackTrace(stack) =>
      stack.mkString("Error: <", "\n", ">")
