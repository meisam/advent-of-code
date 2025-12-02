package year2025.day01

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalacheck.Gen

class Day01Test extends AnyFlatSpec with Matchers with ScalaCheckPropertyChecks:

  "parseLine" should "correctly parse lines starting with L" in:
    forAll(Gen.posNum[Int]): (num: Int) =>
      val line = s"L$num"
      parseLine(line) should be(-num)

  it should "correctly parse lines starting with R" in:
    forAll(Gen.posNum[Int]): (num: Int) =>
      val line = s"R$num"
      parseLine(line) should be(num)

  "countDailOnZero" should "return 1 when landing on zero with anyClickOnZero=false" in:
    forAll(Gen.choose(0, 99), Gen.choose(-1000, 1000)): (pos: Int, rot: Int) =>
      val (cnt, _) = countDailOnZero(false)((0, pos), rot)
      val newPos = math.floorMod(pos + rot, 100)
      if (newPos == 0) cnt should be(1)
      else cnt should be(0)

  it should "count rotations correctly with anyClickOnZero=true" in:
    forAll(Gen.choose(0, 99), Gen.choose(-1000, 1000)): (pos: Int, rot: Int) =>
      val (cnt, _) = countDailOnZero(true)((0, pos), rot)
      val expectedCnt = math.abs((pos + rot) / 100)
      cnt should be(expectedCnt)

  it should "update position correctly" in:
    forAll(Gen.choose(0, 99), Gen.choose(-1000, 1000), Gen.oneOf(true, false)): (pos: Int, rot: Int, click: Boolean) =>
      val (_, newPos) = countDailOnZero(click)((0, pos), rot)
      newPos should be(math.floorMod(pos + rot, 100))
      newPos should be >= 0
      newPos should be < 100

  it should "count rotations correctly when rotating right by multiples of 100" in:
    forAll(Gen.choose(0, 99), Gen.choose(0, 10)): (pos: Int, n: Int) =>
      val rot = n * 100
      val (cnt, _) = countDailOnZero(true)((0, pos), rot)
      cnt should be(n)
