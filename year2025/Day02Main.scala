package year2025
package day02

@main
def main(inputPath: String, isPartOne: Boolean): Unit =
    // In part 1 of the puzzel the product ID can be repeated only two times.
    // In part 2 the product ID can be repeated from 2 to 11 times.
    val allowedRepeats = isPartOne match
      case true => List(2)
      case false => (2 to 11).toList
         
    val invalidIds: Set[Long] = invalidProductIds(allowedRepeats):
        scala.io.Source.fromFile(inputPath)
        .getLines()
        .map(parseLine)
        .next()  // the input has only one line
        .sorted(rangeOrdering)
    println(s"tally = ${invalidIds.sum}")
   