package year2025
package day06

@main
def main(inputPath: String, isPartOne: Boolean): Unit =
  val (nums, operations) = parseInput(io.Source.fromFile(inputPath).getLines().toArray)
  if isPartOne then
    println(simplify(nums, operations))
  else
    val input: Input = io.Source.fromFile(inputPath)
      .getLines()
      .parse

    println(f"result: ${input.result}")