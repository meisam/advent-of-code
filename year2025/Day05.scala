package year2025
package day05

opaque type Ingridient = Long

object Ingridient:
  def apply(ingridientId: Long): Ingridient =
    ingridientId

  def apply(ingridientId: String): Ingridient =
    ingridientId.toLong

opaque type FreshIngridientRange = Tuple2[Ingridient, Ingridient]

object FreshIngridientRange:
  def apply(start: Ingridient, end: Ingridient): FreshIngridientRange =
    (start, end)

extension (self: Array[FreshIngridientRange])
  def contains(ingridient: Ingridient): Boolean =
    self.exists:
      case FreshIngridientRange(start, end) => start <= ingridient && ingridient <= end

def toFreshIngridientRange(str: String): FreshIngridientRange =
  val Array(a, b) = str.split('-')
  val ia = Ingridient(a)
  val ib = Ingridient(b)
  FreshIngridientRange(ia, ib)

def toIngridient(str: String): Ingridient =
  str.toLong