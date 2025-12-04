package year2025
package day03

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalacheck.Gen

def genBatteryPack(size: Int): Gen[BatteryPack] = 
  Gen.listOfN(size, Gen.choose(1, 9)).map(_.mkString).map(_.toBatteryPack)

class Day03Spec extends AnyFlatSpec with Matchers with ScalaCheckPropertyChecks:

  it should "pass the examples in the part ONE problem descrition" in:
    val testCases: Gen[(BatteryPack, Long)] =
      for
        (line, bestJoltage) <- Gen.oneOf(
          ("987654321111111", 98L),
          ("111111111111111", 11L),
          ("811111111111119", 89L),
          ("234234234234278", 78L),
          ("818181911112111", 92L),
        )
      yield (line.toBatteryPack, bestJoltage)

    forAll(testCases):
      (batteryPack: BatteryPack, bestJoltage: Long) =>
        (batteryPack.maxJolt(2)) should equal (bestJoltage)

  it should "pass the examples in the part TWO problem descrition" in:
    val testCases: Gen[(BatteryPack, Long)] =
      for
        (line, bestJoltage) <- Gen.oneOf(
          ("987654321111111", 987_654_321_111L),
          ("811111111111119", 811_111_111_119L),
          ("234234234234278", 434_234_234_278L),
          ("818181911112111", 888_911_112_111L),
        )
      yield (line.toBatteryPack, bestJoltage)
    forAll(testCases):
      (batteryPack: BatteryPack, bestJoltage: Long) =>
        (batteryPack.maxJolt(12)) should equal (bestJoltage)

  "MaxJolt.offer" should "maintain the length of the MaxJolt when offering a battery" in:
    val genMaxJolt: Gen[MaxJolt] = 
      Gen.listOf(Gen.choose(1, 9)).map(_.toArray).map(MaxJolt(_))
    
    forAll(genMaxJolt, Gen.choose(1, 9)): (maxJolt, battery) =>
      whenever(maxJolt.size > 0):
        val updated = maxJolt.offer(battery)
        updated.size should equal (maxJolt.size)

