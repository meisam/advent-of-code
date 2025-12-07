package year2025
package day05

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalacheck.Gen

class Day05Spec extends AnyFlatSpec with Matchers with ScalaCheckPropertyChecks:

  it should "pass the examples in the part ONE problem descrition" in:
    val ranges = Array(
      FreshIngridientRange(Ingridient(3L), Ingridient(5L)),
      FreshIngridientRange(Ingridient(10L), Ingridient(14L)),
      FreshIngridientRange(Ingridient(16L), Ingridient(20L)),
      FreshIngridientRange(Ingridient(12L), Ingridient(18L)),
    )
    val ingridients = Array(
      Ingridient(1L),
      Ingridient(5L),
      Ingridient(8L),
      Ingridient(11L),
      Ingridient(17L),
      Ingridient(32L),
    )

    ingridients.filter(ranges.contains).size should be (3)

  it should "pass the examples in the part TWO problem descrition" in:
    val ranges = Array(
      FreshIngridientRange(Ingridient(3L), Ingridient(5L)),
      FreshIngridientRange(Ingridient(10L), Ingridient(14L)),
      FreshIngridientRange(Ingridient(16L), Ingridient(20L)),
      FreshIngridientRange(Ingridient(12L), Ingridient(18L)),
    )
    ranges
      .consolidate
      .map(_.cardinality)
      .sum should be (14L)

  "colsolidate" should "handle adjacent intervals" in:
    val adjacentRanges: Gen[Array[FreshIngridientRange]] =
      for
        start1 <- Gen.choose(1L, 10L)
        length1 <- Gen.choose(1L, 10L)
        length2 <- Gen.choose(1L, 10L)
        end1 = start1 + length1 - 1
        start2 = end1 + 1
        end2 = start2 + length2 - 1
      yield Array(
        FreshIngridientRange(Ingridient(start1), Ingridient(end1)),
        FreshIngridientRange(Ingridient(start2), Ingridient(end2))
      )

    forAll(adjacentRanges):
      ranges =>
        ranges.consolidate.size should be (1)

  it should "handle all identical intervals" in:
    val identicalRanges: Gen[Array[FreshIngridientRange]] =
      for
        size <- Gen.choose(1, 1000)
        start <- Gen.choose(1L, 10_000L)
        length <- Gen.choose(1L, 10_000L)
        end = start + length - 1
      yield Array.fill(size)(
        FreshIngridientRange(Ingridient(start), Ingridient(end))
      )

    forAll(identicalRanges):
      ranges =>
        ranges.consolidate.size should be (1)
        ranges.consolidate.forall(r => r == ranges.consolidate.head)

  "combine" should "handle overlappnig ranges" in:
    val testData = for
      start1 <- Gen.choose(0L, 10_000L)
      leftDistance <- Gen.choose(0L, 10_000L)
      midDistance <- Gen.choose(0L, 10_000L)
      rightDistance <- Gen.choose(0L, 10_000L)
      end1 = start1 + leftDistance + midDistance
      start2 = start1 + leftDistance
      end2 = start1 + leftDistance + midDistance + rightDistance
    yield (
      FreshIngridientRange(Ingridient(start1), Ingridient(end1)),
      FreshIngridientRange(Ingridient(start2), Ingridient(end2)),
      FreshIngridientRange(Ingridient(start1), Ingridient(end2))
    )

    forAll(testData): (range1, range2, expected) =>
      range1.combine(range2) should be (expected)
      range2.combine(range1) should be (expected)
