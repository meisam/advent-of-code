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
   