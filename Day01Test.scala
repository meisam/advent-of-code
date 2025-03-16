package day01

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

import day01.Error

class Test extends AnyFlatSpec with Matchers with ScalaCheckPropertyChecks:

  "Part1: Parse one line" should "pass" in:
    forAll: (x: Int, value: Int) =>
      day01.parse(s"$x $value") should be(math.abs(x - value))

  "Part1: Parse one line same number" should "result in 0" in:
    forAll: (x: Int) =>
      day01.parse(s"$x $x") should be(0)

  "Part1: swaping numbers in one line" should "result in the same value" in:
    forAll: (x: Int, value: Int) =>
      day01.parse(s"$x $value") should be(day01.parse(s"$value $x"))

  "Part1: Parse multilpe sorted lines" should "pass" in:
    forAll: (x: Int, y: Int) =>
      val value = x / 2
      val diff = y / 4
      day01.parse(s"""
      ${value} ${value + diff}
      ${value + 10} ${value + 10 + diff}
    """.trim) match
        case v: Int => v should be((2 * math.abs(diff)))
        case other  => fail(s"Got an error: $other")

  "Part1: Parse multilpe sorted lines in reverse order" should "pass" in:
    forAll: (x: Int, y: Int) =>
      val value = x / 2
      val diff = y / 4
      day01.parse(s"""
      ${value + 10} ${value + 10 + diff}
      ${value} ${value + diff}
    """.trim) match
        case v: Int => v should be((2 * math.abs(diff)))
        case other  => fail(s"Got an error: $other")

  "Part1: Parse multilpe UN-sorted lines" should "pass" in:
    forAll: (x: Int, y: Int) =>
      val value = x / 2
      val diff = y / 4
      day01.parse(s"""
      ${value} ${value + 10 + diff}
      ${value + 10} ${value + diff}
    """.trim) match
        case v: Int => v should be((2 * math.abs(diff)))
        case other  => fail(s"Got an error: $other")

  "Part1: invalid" should "be parsed as much as possible" in:
    day01.parse("""
    6 7 0
        ^___ invalid input starts here
    irrelevant input
    """".trim) match
      case v: Int => v should be(1)
      case other  => fail(s"Got an error: $other")

  "Part1: failing to parse" should "return 0" in:
    day01.parse("6".trim) match
      case v: Int => v should be(0)
      case other  => fail(s"Got an error: $other")

  "Part1: Jibrish input" should "return 0" in:
    day01.parse("jibrish".trim) match
      case v: Int => v should be(0)
      case other  => fail(s"Got an error: $other")

  "Part2: Parse one line with the same number" should "pass" in:
    forAll: (x: Int) =>
      day01.similarityScore(s"$x $x") match
        case v: Int => v should be(x * 1)
        case other  => fail(s"Got an error: $other")

  "Part2: Parse one line" should "pass" in:
    forAll: (x: Int, value: Int) =>
      day01.similarityScore(s"$x $value") match
        case v: Int if x == value => v should be(x * 1)
        case v: Int               => v should be(0)
        case other                => fail(s"Got an error: $other")

  "Part2: Example in the problem description" should "pass" in:
    day01.similarityScore("""
                            3   4
                            4   3
                            2   5
                            1   3
                            3   9
                            3   3
                            """.trim) match
      case v: Int => v should be(31)
      case other  => fail(s"Got an error: $other")
