package year2025
package day07

import scala.collection.mutable.ArraySeq

enum Cell:
  case `.`, `^`, `S`

import Cell.*

extension (c: Char)
  def asCell: Cell = c match
    case '.' => `.`
    case '^' => `^`
    case 'S' => `S`

type Grid = Array[Array[Cell]]

type Ray = Int

type SplitCount = Int

type RowId = Int

extension (input: Iterator[String])
  def parse: Grid =
    input.map(_.toCharArray.map(_.asCell)).toArray

extension (grid: Grid)
  def startPoint: Ray =
    grid.head.indexOf(`S`)

  def childRays(ray: Ray): Set[Ray] =
    Set(ray-1, ray+1).filter(r => r >= 0 && r < grid.length)

  def cascadeRays(rays: Set[Ray] = Set.empty, rowId: RowId=1, tally: SplitCount = 0): SplitCount =
    rowId == grid.size match
      case true => tally
      case false => 
        val nextRays:Set[Ray] = for
          ray <- rays
          childRay <- if grid(rowId)(ray) == `.` then Set(ray) else (grid.childRays(ray))
        yield childRay

        val splitCnt: SplitCount = rays.filter(ray => grid(rowId)(ray) == `^`).size
        cascadeRays(nextRays, rowId + 1, tally + splitCnt)




