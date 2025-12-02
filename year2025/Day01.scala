package year2025
package day01

val signMap = Map('L' -> -1, 'R' -> 1)

def parseLine(line: String): Int =
  signMap(line.head) * line.tail.toInt

def countDailOnZero(anyClickOnZero: Boolean)(lastCntAndPosition: (Int, Int), rotation: Int): (Int, Int) =
  val (cnt, position) = lastCntAndPosition
  val newPosition = math.floorMod(position + rotation, 100)
  val clicksOnZero =   anyClickOnZero match
    case false => 1 - math.min(1,math.floorMod(position + rotation, 100))
    case true => math.abs((position + rotation) / 100)

  (cnt + clicksOnZero, newPosition)
