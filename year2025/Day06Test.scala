package year2025
package day06

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalacheck.Gen


class Day05Spec extends AnyFlatSpec with Matchers with ScalaCheckPropertyChecks:

  it should "pass the examples in the part ONE problem descrition" in:
    val input =
    """|
       |123 328  51 64 
       | 45 64  387 23 
       |  6 98  215 314
       |*   +   *   +
       |""".stripMargin
    val (nums, operations) = parseInput(input.split("\n").filterNot(_.trim.isEmpty).toArray)
    simplify(nums, operations) should be (4277556L)

  it should "pass the examples in the part TWO problem description" in:
    val input =
      """|123 328  51 64 
         | 45 64  387 23 
         |  6 98  215 314
         |*   +   *   +  """.stripMargin

  "splitIndices" should "pass on the test input" in:
    val input =
      """|123 328  51 64 
         | 45 64  387 23 
         |  6 98  215 314
         |*   +   *   +  """.stripMargin
    input.parse.splitIndices() should be (Array(0, 4, 8, 12, 16))


  "subcalculations" should "split the input correctly" in:
    val input = Array(
      "123 328  51 64 ",
      " 45 64  387 23 ",
      "  6 98  215 314",
      "*   +   *   +  ",
      ).mkString("\n").parse

    val splitIndices = input.splitIndices()
    val got = input.subcalculations(splitIndices)
    val want = Array(
      (Operation.Mul,
        Array(
          Array('1', '2', '3'),
          Array(' ', '4', '5'),
          Array(' ', ' ', '6'),
        )
      ),
      (Operation.Add,
        Array(
          Array('3', '2', '8'),
          Array('6', '4', ' '),
          Array('9', '8', ' '),
        )
      ),
      (Operation.Mul,
        Array(
          Array(' ', '5', '1'),
          Array('3', '8', '7'),
          Array('2', '1', '5'),
        )
      ),
      (Operation.Add,
        Array(
          Array('6', '4', ' '),
          Array('2', '3', ' '),
          Array('3', '1', '4'),
        )
      ),
    )
    for
      ((wantOperation, wantRow), (gotOperation, gotRow)) <- want.zip(got)
      _ = wantOperation should be (gotOperation)
      (wantChar, gotChar) <- wantRow.zip(gotRow)
      _ = wantChar should be (gotChar)
    yield ()

  it should "do the Math correctly the input correctly for one calculation set" in:
    val input = Array(
      "123",
      " 45",
      "  6",
      "*  ",
      ).mkString("\n").parse

    val splitIndices = input.splitIndices()
    input
      .subcalculations(splitIndices)
      .map(_.result)
      .sum should be (8544L)

  it should "do the Math correctly the input correctly" in:
    val input = Array(
      "123 328  51 64 ",
      " 45 64  387 23 ",
      "  6 98  215 314",
      "*   +   *   +  ",
      ).mkString("\n").parse

    val splitIndices = input.splitIndices()
    input.subcalculations(splitIndices).map(_.result)
      .sum should be (3263827L)
