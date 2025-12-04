package year2025
package day03

opaque type MaxJolt = Array[Int]

object MaxJolt:
  def apply(batteries: Array[Int]): MaxJolt = batteries

extension (self: MaxJolt)
  def size: Int = self.length
  def toLong: Long = self.mkString.toLong

  /**
   * Offer a battery to increase the max jolt.
   * The offered battery must be added to the left of the batteries selected for max jolt.
   * If the offered battery can improve the max jolt, it is accepted.
   * Otherwise, the batteries selected for max jolt remains unchanged.
   */
  def offer(battery: Int): MaxJolt =
    // Add the offered battery to the left of the batteries selected for max jolt.
    // Then, stratigically remove one battery to end up with the best possible max jolt.
    // The removed battery may be the offered battery.
    // lhsBatteries are the batteries to the left of the dropped battery.
    val lhsBatteries = (battery +: self)
      .sliding(size=2, step=1)
      .takeWhile:
        case Array(a, b) if a >= b => true
        case _ => false
      .map:
        case Array(a, b) => a
      .toArray
    (lhsBatteries ++ self.drop(lhsBatteries.size)).toArray

opaque type BatteryPack = Array[Int]

extension (str: String)
  def toBatteryPack: BatteryPack = 
    str
      .map(digit => (digit - '0'))
      .toArray

extension (self: BatteryPack)

  def maxJolt(numBatteries: Int): Long =
    @scala.annotation.tailrec
    def recurse(i: Int, maxJolt: MaxJolt): MaxJolt =
      if i < 0 then
        return maxJolt
      val updatedMaxJolt = maxJolt.offer(self(i))
      recurse(i - 1, updatedMaxJolt)
    
    val initialJolt = self.takeRight(numBatteries)
    recurse(self.size - numBatteries - 1, initialJolt).toLong

extension (batteries: Iterator[BatteryPack])
  def totalJoltage(numBatteries: Int): Long =
    batteries
      .map(_.maxJolt(numBatteries))
      .map(_.toLong)
      .sum
