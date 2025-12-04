package year2025
package day03

@main
def main(inputPath: String, isPartOne: Boolean): Unit =
  val maxAllowedBatteries = if isPartOne then 2 else 12
  val totalJoltage = scala.io.Source.fromFile(inputPath)
    .getLines()
    .map(_.toBatteryPack)
    .totalJoltage(maxAllowedBatteries)
  println(totalJoltage)
  