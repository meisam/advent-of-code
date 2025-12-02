package year2025
package day02

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalacheck.Gen

/**
 * Generate a Range(start, end) of product IDs.
 * To ensure that the end is greater than the start,
 * it generates a non-negative start and a positive length.
 * The end is calculated as start + length.
 */
def genRange(): Gen[Range] =
  for
    start <- Gen.choose(0, 1_000_000)
    length <- Gen.choose(100, 1_000_000)
    end = start + length
  yield Range(start, end)

def genRanges(maxNumberOfRanges: Int): Gen[List[Range]] =
  Gen.listOfN(maxNumberOfRanges, genRange())

/**
* Generates an input line.
* The inputline is a comma separated list of rages.
* The ranges are not sorted and may have overlaps.
* Example:
*  2541-2689,11-25,14587-36547,
*/
def genLine(maxRanges: Int): Gen[String] =
  for
    ranges <- Gen.listOfN(maxRanges, genRange())
  yield ranges.map:
    case Range(start, end) => s"$start-$end"
  .mkString(",")

class Day02Spec extends AnyFlatSpec with Matchers with ScalaCheckPropertyChecks:

  it should "pass the examples in the part ONE problem descrition" in:
    val testCases: Gen[(Range, Set[Long])] =
      for
        (range, expectedInvalidIds) <- Gen.oneOf(
          (Range(11L, 22L),  Set(22L, 11L)),
          (Range(95L, 115L),  Set(99L)),
          (Range(998L, 1012L), Set(1010L)),
          (Range(1188511880L, 1188511890L), Set(1188511885L)),
          (Range(222220L, 222224L), Set(222222L)),
          (Range(1698522L, 1698528L), Set.empty[Long]),
          (Range(446443L, 446449L),Set(446446L)),
          (Range(38593856L, 38593862L), Set(38593859L))
        )
      yield (range, expectedInvalidIds)

    forAll(testCases):
      (range: Range, expectedInvalidIds: Set[Long]) =>
        (invalidProductIds(allowedRepeats=Seq(2))(Array(range))) should equal (expectedInvalidIds)

  it should "pass the examples in the part TWO problem descrition" in:
    val testCases: Gen[(Range, Set[Long])] =
      for
        (range, expectedInvalidIds) <- Gen.oneOf(
          (Range(11L, 22L),  Set(22L, 11L)),
          (Range(95L, 115L),  Set(99L, 111L)),
          (Range(998L, 1012L), Set(999L, 1010L)),
          (Range(1188511880L, 1188511890L), Set(1188511885L)),
          (Range(222220L, 222224L), Set(222222L)),
          (Range(1698522L, 1698528L),Set.empty[Long]),
          (Range(446443L, 446449L), Set(446446L)),
          (Range(38593856L, 38593862L), Set(38593859L)),
          (Range(565653L, 565659L), Set(565656L)),
          (Range(824824821L, 824824827L), Set(824824824L)),
          (Range(2121212118L, 2121212124L), Set(2121212121L)),
        )
      yield (range, expectedInvalidIds)

    forAll(testCases):
      (range: Range, expectedInvalidIds: Set[Long]) =>
        (invalidProductIds((2 to 11))(Array(range))) should equal (expectedInvalidIds)

  it should "pass parse the correct number of ranges" in:
    val testData = for
        numberOfRanges <- Gen.choose(1, 40)
        line <- genLine(numberOfRanges)
    yield (numberOfRanges, line)
    forAll(testData):
      (numberOfRanges: Int, line: String) =>
          val ranges = parseLine(line)
          ranges should have length numberOfRanges

  "invalidProductIds" should "pass basic tests" in:
    val testData = for
        range <- Gen.oneOf(List(Range(11L, 22L), Range(95L, 115L)))
    yield range
    forAll(testData):
      (range: Range) =>
          val countOfInvalid = invalidProductIds(allowedRepeats=List(2))(Array(range).sorted(rangeOrdering))
          (countOfInvalid.size) should be < (1000)

  "invalidProductIds" should "return 1 for Range(`aa`-1, `aa`+1)" in:
    val testData: Gen[Range] = for
      productId <- Gen.choose(1, 100)
      invalidProductId = s"$productId$productId".toLong
      range =  Range(invalidProductId - 1L, invalidProductId + 1L)
    yield range
    forAll(testData):
      (range: Range) =>
        invalidProductIds(allowedRepeats=List(2))(Array(range)) should equal (Set(range.start + 1))

  it should "return 1 for Range(`aa`, `aa`+1)" in:
    val testData: Gen[Range] = for
      productId <- Gen.choose(1, 100)
      invalidProductId = s"$productId$productId".toLong
      range =  Range(invalidProductId, invalidProductId + 1L)
    yield range
    forAll(testData):
      (range: Range) =>
        invalidProductIds(allowedRepeats=List(2))(Array(range)) should equal (Set(range.start))

  it should "return 1 for Range(`aa`-1, `aa`)" in:
    val testData = for
      productId <- Gen.choose(1, 100)
      invalidProductId = s"$productId$productId".toLong
      range = Range(invalidProductId - 1, invalidProductId)
    yield range
    forAll(testData):
      (range: Range) =>
        invalidProductIds(allowedRepeats=List(2))(Array(range)) should equal (Set(range.end))
