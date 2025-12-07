package year2025
package day05

import scala.util.ChainingOps.given

@main
def main(inputPath: String, isPartOne: Boolean): Unit =
  val _ = if isPartOne then 2 else 12
  val inputLines = scala.io.Source.fromFile(inputPath)
    .getLines()
    .toArray
  val freshIngridientRanges: Array[FreshIngridientRange] = inputLines
    .takeWhile(_.nonEmpty)
    .map(toFreshIngridientRange)
    .toArray

  isPartOne match
    case true =>
      val ingridients = inputLines
        .drop(freshIngridientRanges.size)
        .tail
        .map(toIngridient)
        .toArray

      val countOfFreshIngridients = ingridients.filter(freshIngridientRanges.contains).size

      println(s"tally = $countOfFreshIngridients")
    case false =>
      val freshIngridientCount: Long = freshIngridientRanges
        .consolidate
        .map[Long](_.cardinality)
        .sum
      println(s"tally (PART 2) = $freshIngridientCount")
