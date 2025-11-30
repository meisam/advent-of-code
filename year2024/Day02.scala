package day02

import parser.StringParsers
import StringParsers.*

opaque type Error = String

val inputParser: Parser[List[List[Int]]] =
  (int.map(_.toInt).sep1(char(' '))).sep1(char('\n'))

def parse(input: String): List[List[Int]] =
  inputParser.run(input) match
    case numbers: List[List[Int]] => numbers
    case _                        => Nil

/** A report only counts as safe if both of the following are true:
  *   1. The levels are either all increasing or all decreasing. 2. Any two
  *      adjacent levels differ by at least one and at most three.
  * @example
  *   - isSafe(7, 6, 4, 2, 1): true because the levels are all decreasing by 1
  *     or 2.
  *   - isSafe(1, 2, 7, 8, 9): false because 2 7 is an increase of 5.
  *   - isSafe(9, 7, 6, 2, 1): false because 6 2 is a decrease of 4.
  *   - isSafe(1, 3, 2, 4, 5): false because 1 3 is increasing but 3 2 is
  *     decreasing.
  *   - isSafe(8, 6, 4, 4, 1): false because 4 4 is neither an increase or a
  *     decrease.
  *   - isSafe(1, 3, 6, 7, 9): true because the levels are all increasing by 1,
  *     2, or 3.
  *
  * @param records
  * @return
  *   true if the report is safe, false otherwise.
  */
def isSafe(records: List[Int]): Boolean =
  println(s"XXX: $records")
  val (_, increasing, decreasing) = records match
    case Nil => (0, true, true) // empty list is considered safe
    case head :: tail =>
      tail.foldLeft(
        ( /*last=*/ head, /*increasing=*/ true, /*decreasing=*/ true)
      ):
        case ((_, false, false), element) => (element, false, false)
        case ((last, increasing, decreasing), element) =>
          (
            element,
            increasing && last < element && last + 3 <= element,
            decreasing && last > element && last - 3 >= element
          )
  increasing || decreasing
  true

def countSafeRecords(input: String): Int =
  parse(input).count(isSafe)
