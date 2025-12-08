package year2025
package day06

import scala.jdk.CollectionConverters.*

enum Operation:
  case Add, Mul

extension (c: Char)
  def toOperation: Operation =
    c match
      case '+' => Operation.Add
      case '*' => Operation.Mul
    
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

opaque type Input = Array[Array[Char]]
opaque type Calculation = (Operation, Input)
extension (self: String)
  def parse: Input =
    self
      .split('\n')
      .map((line: String) => line.toCharArray())
      .toArray

extension (self: Iterator[String])
  def parse: Input =
    self
      .map((line: String) => line.toCharArray())
      .toArray

extension (self: Input)
  @scala.annotation.tailrec
  def splitIndices(j: Int = 0, lastPartitionIndex: Int = 0, accum: Array[Int] = Array(0)): Array[Int] =
    if j >= self.head.length then return accum :+ (self.head.length + 1)
    self.forall(row => row(j) == ' ' ) match
      case true =>  splitIndices(j + 2, j, accum :+ (j + 1))
      case false => splitIndices(j + 1, lastPartitionIndex, accum)

  def subcalculations(splitIndices: Array[Int]): Array[Calculation] =
    for
      (startJ, endJ) <- splitIndices.zip(splitIndices.drop(1))
      operation = self.last(startJ).toOperation
      subInput = Array.tabulate(self.length - 1, endJ - startJ - 1):
        (i, j) => self(i)(j + startJ)
    yield
      (operation, subInput)

  def result: Long =
    self
    .subcalculations(self.splitIndices())
    .map(_.result)
    .sum

extension (calculation: Calculation)
  def result: Long =
    val numbers: Array[Long] = calculation._2
      .transpose()
      .map[Long]:
        chars =>
          chars
          .filter(_.isDigit)
          .mkString.toLong
    calculation match
      case (Operation.Add, _) => numbers.sum
      case (Operation.Mul, _) => numbers.product
