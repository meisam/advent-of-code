package day01
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers

class Test extends AnyFlatSpec with Matchers:
  "Part1: Parse one line" should "pass" in:
    day01.parse("5 7") must be(2)
    day01.parse("7 5") must be(2)
    day01.parse("5 5") must be(0)
    day01.parse("7 7") must be(0)

  "Part1: Parse multilpe sorted lines" should "pass" in:
    day01.parse("""
    5 7
    6 8
    """".trim) must be(4)

  "Part1: Parse multilpe reverse sorted lines" should "pass" in:
    day01.parse("""
    6 8
    5 7
    """".trim) must be(4)

  "Part1: Parse multilpe UN-sorted lines" should "pass" in:
    day01.parse("""
    5 8
    6 7
    """".trim) must be(4)

  "Part1: Parse multilpe UN-sorted lines in any order" should "pass" in:
    day01.parse("""
    6 7
    5 8
    """".trim) must be(4)

  "Part1: invalid" should "be parsed as much as possible" in:
    day01.parse("""
    6 7 0
        ^___ invalid input starts here
    irrelevant input
    """".trim) must be(1)

  "Part1: failing to parse" should "return 0" in:
    day01.parse("6".trim) must be(0)

  "Part1: Jibrish input" should "return 0" in:
    day01.parse("jibrish".trim) must be(0)

  "Part2: Parse one line" should "pass" in:
    day01.similarityScore("5 7") must be(0)
    day01.similarityScore("5 5") must be(5)
    day01.similarityScore("7 7") must be(7)
    day01.similarityScore("""
    7 5
    5 7
    """.trim) must be(12)

  "Part2: Example in the problem description" should "pass" in:
    day01.similarityScore("""
    3   4
    4   3
    2   5
    1   3
    3   9
    3   3
    """.trim) must be(31)
