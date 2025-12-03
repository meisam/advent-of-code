package year2025
package day03

@main
def main(inputPath: String, isPartOne: Boolean): Unit =
  val totalJoltage = scala.io.Source.fromFile(inputPath)
    .getLines()
    .map(_.toBatteryPack)
    .totalJoltage
  println(totalJoltage)
  