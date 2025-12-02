package year2025
package day01

@main
def main(path: String, anyClickOnZero: Boolean): Unit =
  val lines = io.Source.fromFile(path).getLines()
  val (finalCount, finalPosition) = lines
    .map(parseLine)
    .foldLeft[(Int, Int)]((0, 50))(countDailOnZero(anyClickOnZero))
  println(s"Final position: $finalPosition, final count: $finalCount")
      
  