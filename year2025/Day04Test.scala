package year2025
package day04

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalacheck.Gen

class Day04Spec extends AnyFlatSpec with Matchers with ScalaCheckPropertyChecks:

  it should "pass the examples in the part ONE problem descrition" in:
    val testInput = Array(
      "..@@.@@@@.",
      "@@@.@.@.@@",
      "@@@@@.@.@@",
      "@.@@@@..@.",
      "@@.@@@@.@@",
      ".@@@@@@@.@",
      ".@.@.@.@@@",
      "@.@@@.@@@@",
      ".@@@@@@@@.",
      "@.@.@@@.@.",
    )

    testInput.iterator.toWall.countAccessibleRols(cascade=false) should equal (13)

  it should "pass the examples in the part TWO problem descrition" in:
    val testInput = Array(
      "..@@.@@@@.",
      "@@@.@.@.@@",
      "@@@@@.@.@@",
      "@.@@@@..@.",
      "@@.@@@@.@@",
      ".@@@@@@@.@",
      ".@.@.@.@@@",
      "@.@@@.@@@@",
      ".@@@@@@@@.",
      "@.@.@@@.@.",
    )

    testInput.iterator.toWall.countAccessibleRols(cascade=true) should equal (43)
