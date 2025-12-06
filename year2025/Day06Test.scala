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