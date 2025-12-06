package year2025
package day06

@main
def main(inputPath: String, isPartOne: Boolean): Unit =
  val (nums, operations) = parseInput(io.Source.fromFile(inputPath).getLines().toArray)
  nums.foreach(_.foreach(println))
  operations.foreach(println)
  if isPartOne then
    println(simplify(nums, operations))
  else
    println(???)