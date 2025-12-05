package year2025
package day05

@main
def main(inputPath: String, isPartOne: Boolean): Unit =
  val _ = if isPartOne then 2 else 12
  val inputLines = scala.io.Source.fromFile(inputPath)
    .getLines()
    .toArray
  val freshIngridientRanges = inputLines
    .takeWhile(_.nonEmpty)
    .map(toFreshIngridientRange)
    .toArray

  val ingridients = inputLines
    .drop(freshIngridientRanges.size)
    .tail
    .map(toIngridient)
    .toArray

  val countOfFreshIngridients = ingridients.filter(freshIngridientRanges.contains).size

  println(s"tally = $countOfFreshIngridients")
    