package year2025
package day04

@main
def main(inputPath: String, isPartOne: Boolean): Unit =
      
  val accessibleCount: Long = scala.io.Source.fromFile(inputPath)
    .getLines()
    .toWall
    .countAccessibleRols
  println(s"tally = ${accessibleCount}")
   