package year2025
package day03

case class MaxJolt(leftDigit: Int, rightDigit: Int)

extension (self: MaxJolt)
  def toInt: Int = self.leftDigit * 10 + self.rightDigit

case class BatteryPack(batteries: Array[Int])

extension (str: String)
  def toBatteryPack: BatteryPack = 
    BatteryPack:
      str
        .map(digit => (digit - '0'))
        .toArray

extension (self: BatteryPack)
  def apply(i: Int): Int = self.batteries(i)
  def size: Int = self.batteries.size
  def maxJolt: Int =
    @scala.annotation.tailrec
    def recurse(i: Int, maxJolt: MaxJolt): MaxJolt =
      if i >= self.size then
        return maxJolt
      val v = self(i)
      maxJolt match
        case MaxJolt(l, r) if l < r => recurse(i+1, MaxJolt(r, v))
        case MaxJolt(l, r) => recurse(i+1, MaxJolt(l, v.max(r)))

    recurse(0, MaxJolt(0, 0)).toInt

extension (batteries: Iterator[BatteryPack])
  def totalJoltage: Int =
    batteries
      .map(_.maxJolt)
      .map(_.toInt)
      .sum

