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
    val testCases: Gen[(BatteryPack, Int)] =
      for
        (line, bestJoltage) <- Gen.oneOf(
          ("987654321111111", 98),
          ("811111111111119", 89),
          ("234234234234278", 78),
          ("818181911112111", 92),
        )
      yield (line.toBatteryPack, bestJoltage)

    forAll(testCases):
      (batteryPack: BatteryPack, bestJoltage: Int) =>
        (maxJolt(batteryPack)) should equal (bestJoltage)
