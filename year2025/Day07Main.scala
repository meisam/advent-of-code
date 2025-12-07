package year2025
package day07

@main
def main(inputPath: String, isPartOne: Boolean): Unit =
  val input: Iterator[String] = scala.io.Source.fromFile(inputPath).getLines()
  val grid = input.parse

  println(grid.cascadeRays(Set(grid.startPoint)))
