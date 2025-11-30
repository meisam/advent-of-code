package day02

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

import day02.Error

class Part1Tests
    extends AnyFlatSpec
    with Matchers
    with ScalaCheckPropertyChecks:
  "Parsing one line " should "pass" in:
    day02.parse("1 2 3 4 5 \n") match
      case Nil =>
        fail("Parsing failed, expected list of lists, got empty list")
      case x @ h:: tail =>
        x should contain theSameElementsAs (List(1, 2, 3, 4, 5))
      case error => fail(s"Got an error: $error")

  // "Example in problem description" should "return 2" in:
  //   day02
  //     .countSafeRecords(
  //       """7 6 4 2 1
  //       |1 2 7 8 9
  //       |9 7 6 2 1
  //       |1 3 2 4 5
  //       |8 6 4 4 1
  //       |1 3 6 7 9
  //       |""".stripMargin
  //     ) match
  //     case v: Int => v should be(2)
  //     case error  => fail(s"Got an error: $error")
