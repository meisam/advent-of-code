package year2025
package day02

import scala.collection.mutable.ArraySeq

case class Range(start: Long, end: Long)

val rangeOrdering = Ordering.by[Range, Long](_.start)

/**
 * Parse a line of text into a list of ranges.
 */
def parseLine(line: String): ArraySeq[Range] =
  line
    .split(",")
    .map(_.split("-").map(_.toLong))
    .map:
      case Array(start, end) => Range(start, end)

/**
 * Returns a set of invalid product IDs.
 * Preconditions:
 * - sortedRanges must not have overlapping ranges.
 * - sortedRanges must be sorted in ascending order (by the start of the ranges).
 *
 * Returns a Set[Long] to avoid double counting. E.g.:
 * - 11111 can be considered 1 repeated 4 times or 11 repeated 2 times.
 */
def invalidProductIds(allowedRepeats: Seq[Int])(sortedRanges: ArraySeq[Range]): Set[Long] =
  
  // Start from smallest guess for product Id and the first range
  // Check if repeating the product ID makes a number within the range.
  // Three cases are possible (because the ranges are sorted):
  // 1. The repeated product ID is after the range => try the next range
  // 2. The repeated product ID is before the range => try a bigger product Id.
  // 3. The repeated product ID is within the range => keep it in the tally.
  @scala.annotation.tailrec
  def recurse(i: Int, repeatCount: Int, productIdGuess: Long, tally: List[Long]): List[Long] =
    val repeated = (f"$productIdGuess" * repeatCount).toLong
    if i >= sortedRanges.length then
      return tally
    if repeated > sortedRanges(i).end then
      recurse(i + 1, repeatCount, productIdGuess, tally)
    else if repeated < sortedRanges(i).start then
      recurse(i, repeatCount, productIdGuess + 1, tally)
    else 
      recurse(i, repeatCount, productIdGuess + 1,  repeated :: tally)

  (for
    repeatCount <- allowedRepeats
    invalidProductId <- recurse(i=0, repeatCount, productIdGuess=1L, tally=Nil)
  yield invalidProductId).toSet