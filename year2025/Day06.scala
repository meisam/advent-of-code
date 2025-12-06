package year2025
package day06

enum Operation:
  case Add, Mul
def parseInput(input: Array[String]): (Array[Array[Long]], Array[Operation]) =
  val nums = input
    .dropRight(1)
    .map: line =>
      line.split(" ").filterNot(_.trim.isEmpty).map(_.toLong).toArray 
    .toArray
  val operations = input
    .last
    .split(" ").filterNot(_.trim.isEmpty)
    .map: op =>
      op match
        case "+" => Operation.Add
        case "*" => Operation.Mul
    .toArray
  (nums, operations)

def simplify(nums: Array[Array[Long]], operations: Array[Operation]): Long =
  (for
    (row, op) <- nums.transpose.zip(operations)
  yield
    op match
      case Operation.Add => row.sum
      case Operation.Mul => row.product)
  .sum