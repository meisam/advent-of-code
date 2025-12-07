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

extension (self: FreshIngridientRange)
  def begin: Ingridient = self._1
  def end: Ingridient = self._2
  def combine(other: FreshIngridientRange): FreshIngridientRange =
    FreshIngridientRange(self.begin.min(other.begin), self.end.max(other.end))
  def cardinality: Long =
    self.end - self.begin + 1L

  def contains(ingridient: Ingridient): Boolean =
    self.begin <= ingridient && ingridient <= self.end

extension (self: Array[FreshIngridientRange])
  def contains(ingridient: Ingridient): Boolean =
    self.exists:
      case FreshIngridientRange(start, end) => start <= ingridient && ingridient <= end

  def consolidate: Array[FreshIngridientRange] =
    val sortedRanges = self
      .sortBy(_.begin)
    var res = Array.empty[FreshIngridientRange]()
    var last = FreshIngridientRange(
      Ingridient(sortedRanges.head.begin - 1L),
      Ingridient(sortedRanges.head.begin - 1L))

    def recurse(i: Int, accum: List[FreshIngridientRange]): List[FreshIngridientRange] =
      if i >= sortedRanges.length then return accum
      val thisRange = sortedRanges(i)
      accum match
        case prev::rest if thisRange.begin <= prev.end + 1L =>
          recurse(i + 1, prev.combine(thisRange)::rest)
        case _ =>
          recurse(i + 1, thisRange:: accum)
    
    recurse(0, Nil).toArray

def toFreshIngridientRange(str: String): FreshIngridientRange =
  val Array(a, b) = str.split('-')
  val ia = Ingridient(a)
  val ib = Ingridient(b)
  FreshIngridientRange(ia, ib)

def toIngridient(str: String): Ingridient =
  str.toLong

